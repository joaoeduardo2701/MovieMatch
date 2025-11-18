const BASE_URL = "http://localhost:8080";

// ==============================
// Auxiliar: parse seguro de resposta
// ==============================
async function parseResponseSafe(res) {
  // lê como texto (pode ser "" , texto simples, ou JSON)
  const text = await res.text();
  if (!text) return null; // vazio (204, por exemplo)

  // tenta fazer parse JSON; se falhar, devolve o texto cru
  try {
    return JSON.parse(text);
  } catch (err) {
    return text;
  }
}

// ==============================
// FILMES
// ==============================
export async function getFilmes() {
  const res = await fetch(`${BASE_URL}/filmes`);
  if (!res.ok) throw new Error("Erro ao buscar filmes");
  return parseResponseSafe(res);
}

export async function getFilmeById(id) {
  const res = await fetch(`${BASE_URL}/filmes/${id}`);
  if (!res.ok) throw new Error("Erro ao buscar filme");
  return parseResponseSafe(res);
}

// ==============================
// AVALIAÇÕES
// ==============================
export async function getAvaliacoesByFilme(idFilme) {
  const res = await fetch(`${BASE_URL}/avaliacoes/filmes/${idFilme}`);
  if (!res.ok) {
    const txt = await res.text();
    console.error("Erro ao buscar avaliações:", res.status, txt);
    throw new Error("Erro ao buscar avaliações do filme");
  }

  // se API retornar vazio -> devolver array vazio
  const parsed = await parseResponseSafe(res);
  return Array.isArray(parsed) ? parsed : [];
}

export async function postarAvaliacao(idUsuario, idFilme, avaliacao) {
  const url = `${BASE_URL}/avaliacoes/usuarios/${idUsuario}/filmes/${idFilme}`;

  const res = await fetch(url, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(avaliacao)
  });

  // se erro HTTP -> tenta logar corpo e lançar
  if (!res.ok) {
    const text = await res.text();
    console.error("Erro da API (POST):", res.status, text);
    throw new Error("Erro ao enviar avaliação");
  }

  // parse seguro: pode ser JSON, string, ou vazio
  const parsed = await parseResponseSafe(res);
  return parsed ?? { sucesso: true };
}

export async function getUsuarios() {
    const res = await fetch(`${BASE_URL}/usuarios`);
    if (!res.ok) throw new Error("Erro ao buscar usuários");
    return res.json();
}

