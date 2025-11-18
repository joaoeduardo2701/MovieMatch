import { getFilmes } from "./api.js";

async function carregarFilmes() {
  try {
    const filmes = await getFilmes();
    const container = document.getElementById("listaFilmes");
    container.innerHTML = "";

    filmes.forEach(filme => {
      const card = document.createElement("div");
      card.classList.add("filme-card");

      // Deixar o card clicável
      card.style.cursor = "pointer";
      card.addEventListener("click", () => {
        window.location.href = `detalhes.html?id=${filme.id}`;
      });

      const tagsHTML = (filme.tags || [])
        .map(tag => `<span class="tag">${tag}</span>`)
        .join("");

      card.innerHTML = `
        <img src="${filme.imagemUrl}" alt="${filme.titulo}">
        <div class="filme-info">
          <h2>${filme.titulo}</h2>
          <p><strong>Gênero:</strong> ${filme.genero?.replace("_", " ") || "Desconhecido"}</p>
          <p>${filme.descricao || "Sem descrição disponível."}</p>
          <div class="tags">${tagsHTML}</div>
        </div>
      `;

      container.appendChild(card);
    });
  } catch (erro) {
    console.error("Erro ao carregar filmes:", erro);
  }
}

document.addEventListener("DOMContentLoaded", carregarFilmes);
