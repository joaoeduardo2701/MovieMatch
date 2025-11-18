document.getElementById("loginForm").addEventListener("submit", function (event) {
  event.preventDefault();

  const email = document.getElementById("email").value;
  const senha = document.getElementById("senha").value;
  const erroMsg = document.getElementById("erro");

  // Simulação de usuário vindo da API
  const usuarioFake = {
    id: 1,
    nome: "Dudu",
    email: "dudu@exemplo.com",
    senha: "123456",
    generosFavoritos: ["ACAO", "FICCAO_CIENTIFICA"],
    filmesAssistidos: [
      {
        id: 1,
        titulo: "Interestelar",
        genero: "FICCAO_CIENTIFICA",
        imagemUrl: "https://br.web.img3.acsta.net/pictures/14/10/31/20/39/476171.jpg"
      },
      {
        id: 2,
        titulo: "O Poderoso Chefão",
        genero: "DRAMA",
        imagemUrl: "https://br.web.img3.acsta.net/medias/nmedia/18/90/93/20/20120876.jpg"
      },
      {
        id: 3,
        titulo: "Vingadores: Ultimato",
        genero: "ACAO",
        imagemUrl: "https://m.media-amazon.com/images/I/81ExhpBEbHL._AC_SL1500_.jpg"
      }
    ]
  };

  if (email === usuarioFake.email && senha === usuarioFake.senha) {
    // Salva o usuário no localStorage
    localStorage.setItem("usuarioLogado", JSON.stringify(usuarioFake));
    window.location.href = "index.html"; // redireciona para a home
  } else {
    erroMsg.textContent = "E-mail ou senha incorretos!";
  }
});
