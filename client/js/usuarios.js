// Array fixo de imagens — posição 0 para usuário 1, posição 1 para usuário 2...
const imagensUsuarios = [
    "https://pbs.twimg.com/media/E9bEa31XMAwGf49.jpg",
    "https://midias.correiobraziliense.com.br/_midias/jpg/2022/05/20/1000x1000/1_velma_scooby_doo-25699115.jpg?20220520180255?20220520180255",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8LljSb5gOWcDWzJmas3QZ0j-7h4C5sL4ryw&shttps://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8LljSb5gOWcDWzJmas3QZ0j-7h4C5sL4ryw&s",
    "img/user4.png"
];

// Função para carregar usuários da API
async function carregarUsuarios() {
    try {
        const response = await fetch("http://localhost:8080/usuarios");

        if (!response.ok) {
            throw new Error("Erro ao buscar usuários");
        }

        const usuarios = await response.json();
        const container = document.getElementById("listaUsuarios");
        container.innerHTML = "";

        usuarios.forEach((usuario, index) => {
            const imagem = imagensUsuarios[index] || "img/default.png";

            // caso você ainda não calcule compatibilidade, deixa 100% fixo
            const compat = Math.floor(Math.random() * (100 - 60 + 1)) + 60;


            const card = `
                <div class="usuario-card">
                    <img class="foto-perfil" src="${imagem}" class="foto-usuario" alt="Foto do usuário">

                    <h2>${usuario.nome}</h2>

                    <p><strong>Email:</strong> ${usuario.email}</p>

                    <p><strong>Gêneros favoritos:</strong> 
                        <span class="tag">${usuario.generosFavoritos?.join(", ") || "Nenhum"}</span>
                    </p>

                    <p class="compat">🔥 Compatibilidade: <strong>${compat}%</strong></p>

                    <button class="btn-ver-perfil" onclick="window.location.href='perfil.html?id=${usuario.id}'">
                        Ver Perfil
                    </button>
                </div>
            `;

            container.innerHTML += card;
        });

    } catch (erro) {
        console.error("Erro:", erro);
    }
}

// Carregar ao abrir página
carregarUsuarios();
