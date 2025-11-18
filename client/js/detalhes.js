import { 
    getFilmeById, 
    getAvaliacoesByFilme, 
    postarAvaliacao 
} from "./api.js";

// ==============================
// Carregar detalhes do filme
// ==============================
async function carregarDetalhes() {
    const params = new URLSearchParams(window.location.search);
    const idFilme = Number(params.get("id"));

    if (!idFilme) {
        console.error("ID do filme inválido");
        return;
    }

    try {
        const filme = await getFilmeById(idFilme);

        document.getElementById("tituloFilme").textContent = filme.titulo;
        document.getElementById("posterFilme").src = filme.imagemUrl;
        document.getElementById("posterFilme").alt = filme.titulo;
        document.getElementById("descricaoFilme").textContent = filme.descricao;
        document.getElementById("generoFilme").textContent = filme.genero.replace("_", " ");
        document.getElementById("anoFilme").textContent = filme.ano ?? "Não informado";

        await carregarAvaliacoes(idFilme);

    } catch (erro) {
        console.error("Erro ao carregar detalhes:", erro);
    }
}

// ==============================
// Carregar avaliações do filme
// ==============================
async function carregarAvaliacoes(idFilme) {
    try {
        const avaliacoes = await getAvaliacoesByFilme(idFilme);

        const area = document.getElementById("listaAvaliacoes");
        area.innerHTML = "";

        if (!Array.isArray(avaliacoes) || avaliacoes.length === 0) {
            area.innerHTML = "<p>Nenhuma avaliação ainda.</p>";
            return;
        }

        avaliacoes.forEach(av => {
            const div = document.createElement("div");
            div.classList.add("avaliacao-item");

            div.innerHTML = `
                <p><strong>⭐ ${av.estrelas}</strong></p>
                <p>${av.comentario}</p>
                <hr>
            `;

            area.appendChild(div);
        });

    } catch (erro) {
        console.error("Erro ao carregar avaliações:", erro);
    }
}

// ==============================
// Enviar NOVA avaliação
// ==============================
document.getElementById("formAvaliacao").addEventListener("submit", async (e) => {
    e.preventDefault();

    const params = new URLSearchParams(window.location.search);
    const idFilme = Number(params.get("id"));
    const idUsuario = 1; // temporário

    const estrelas = Number(document.getElementById("estrelas").value);
    const comentario = document.getElementById("comentario").value.trim();

    if (!estrelas || !comentario) {
        document.getElementById("erroAvaliacao").textContent = "Preencha todos os campos.";
        return;
    }

    try {
        console.log("📤 Enviando avaliação...");
        
        const resposta = await postarAvaliacao(idUsuario, idFilme, { estrelas, comentario });

        console.log("📥 Resposta da API:", resposta);

        document.getElementById("erroAvaliacao").textContent = "";
        e.target.reset();

        await carregarAvaliacoes(idFilme); // atualizar lista

    } catch (erro) {
        console.error("ERRO REAL AO POSTAR:", erro);
        document.getElementById("erroAvaliacao").textContent = "Erro ao enviar avaliação.";
    }
});

document.addEventListener("DOMContentLoaded", carregarDetalhes);
