package com.moviematch.moviematch.config;

import com.moviematch.moviematch.model.Filme;
import com.moviematch.moviematch.enums.Genero;
import com.moviematch.moviematch.repository.FilmeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final FilmeRepository filmeRepository;

    public DataLoader(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Override
    public void run(String... args) {
        if (filmeRepository.count() == 0) {

            List<Filme> filmes = List.of(
                    new Filme(null, "Interestelar",
                            Genero.FICCAO_CIENTIFICA,
                            "Uma jornada pelo espaço e tempo em busca de um novo lar para a humanidade.",
                            0.0,
                            List.of("espaço","drama","ficção"),
                            "https://br.web.img3.acsta.net/pictures/14/10/31/20/39/476171.jpg"),
                    new Filme(null, "O Poderoso Chefão",
                            Genero.DRAMA,
                            "A história da família mafiosa Corleone.",
                            0.0,
                            List.of("máfia","clássico"),
                            "https://br.web.img3.acsta.net/medias/nmedia/18/90/93/20/20120876.jpg"),
                    new Filme(null, "Vingadores: Ultimato",
                            Genero.ACAO,
                            "Os heróis enfrentam Thanos em uma batalha épica.",
                            0.0,
                            List.of("super-herói","ação","marvel"),
                            "https://m.media-amazon.com/images/I/81ExhpBEbHL._AC_SL1500_.jpg"),
                    new Filme(null, "Toy Story",
                            Genero.ANIMACAO,
                            "Os brinquedos ganham vida quando os humanos não estão olhando.",
                            0.0,
                            List.of("animação","família"),
                            "https://uauposters.com.br/media/catalog/product/2/2/227020140608-uau-posters-filmes-infantis-animacao-toy-story-toy-story--1.jpg"),
                    new Filme(null, "Titanic",
                            Genero.ROMANCE,
                            "Um romance épico a bordo do navio mais famoso do mundo.",
                            0.0,
                            List.of("romance","drama"),
                            "https://img.elo7.com.br/product/main/3A58314/poster-cartaz-adesivo-decorativo-titanic-42-5x60cm-decoracao.jpg"),
                    new Filme(null, "O Exorcista",
                            Genero.TERROR,
                            "Um clássico do terror que marcou gerações.",
                            0.0,
                            List.of("terror","clássico"),
                            "https://static.wikia.nocookie.net/dublagempedia/images/3/35/The-exorcist-poster-1_%281%29.jpg/revision/latest?cb=20210208171854&path-prefix=pt-br"),
                    new Filme(null, "Matrix",
                            Genero.FICCAO_CIENTIFICA,
                            "Um hacker descobre a verdade sobre a realidade em que vive.",
                            0.0,
                            List.of("ação","ficção"),
                            "https://m.media-amazon.com/images/I/51EG732BV3L.jpg"),
                    new Filme(null, "Forrest Gump",
                            Genero.DRAMA,
                            "A vida extraordinária de um homem simples.",
                            0.0,
                            List.of("drama","comédia"),
                            "https://m.media-amazon.com/images/I/613ZgTigTpL.jpg"),
                    new Filme(null, "Homem-Aranha: Sem Volta Para Casa",
                            Genero.ACAO,
                            "Peter Parker enfrenta o multiverso.",
                            0.0,
                            List.of("super-herói","ação"),
                            "https://upload.wikimedia.org/wikipedia/pt/thumb/0/00/Spider-Man_No_Way_Home_poster.jpg/250px-Spider-Man_No_Way_Home_poster.jpg"),
                    new Filme(null, "Jurassic Park",
                            Genero.AVENTURA,
                            "Dinossauros voltam à vida em um parque temático.",
                            0.0,
                            List.of("aventura","sci-fi"),
                            "https://m.media-amazon.com/images/I/611AicXY6XL._UF894,1000_QL80_.jpg"),
                    new Filme(null, "Gladiador",
                            Genero.ACAO,
                            "Um guerreiro em busca de vingança.",
                            0.0,
                            List.of("épico","ação"),
                            "https://m.media-amazon.com/images/S/pv-target-images/03888657ce83be516e96690e7921c71a06447fc2d20ef8c7c358962515f6c85f.jpg"),
                    new Filme(null, "A Origem",
                            Genero.FICCAO_CIENTIFICA,
                            "Sonhos dentro de sonhos — um thriller psicológico.",
                            0.0,
                            List.of("thriller","ficção"),
                            "https://m.media-amazon.com/images/I/61AFbsFwh7L._AC_UF894,1000_QL80_.jpg"),
                    new Filme(null, "Procurando Nemo",
                            Genero.ANIMACAO,
                            "Um pai em busca do filho pelo oceano.",
                            0.0,
                            List.of("animação","família"),
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTV0SxHJnvBjc37MjsaejLNufySDxc74k6bnzz6m_3m8Qb-19OqNCFBgk43Sl8TGpIFPC8n-Dn1ywdbZ0uSJBpc7QLKhUyQIZgC6hb69Q&s=10"),
                    new Filme(null, "Pulp Fiction",
                            Genero.DRAMA,
                            "Histórias interligadas e violência estilizada.",
                            0.0,
                            List.of("cult","crime"),
                            "https://m.media-amazon.com/images/I/71c05lTE03L._AC_SL1024_.jpg"),
                    new Filme(null, "Clube da Luta",
                            Genero.DRAMA,
                            "O clube mais secreto do mundo.",
                            0.0,
                            List.of("drama","cult"),
                            "https://m.media-amazon.com/images/I/51v5ZpFyaFL._AC_.jpg"),
                    new Filme(null, "Avatar",
                            Genero.FICCAO_CIENTIFICA,
                            "Conflito em Pandora — espetáculo visual.",
                            0.0,
                            List.of("ficção","aventura"),
                            "https://img.elo7.com.br/product/zoom/46B9275/big-poster-filme-avatar-tamanho-90x60-cm-lo003-presente-geek.jpg"),
                    new Filme(null, "Pantera Negra",
                            Genero.ACAO,
                            "O herói de Wakanda.",
                            0.0,
                            List.of("ação","super-herói"),
                            "https://cdn.awsli.com.br/600x700/1181/1181256/produto/85785571cdbdca6ead.jpg"),
                    new Filme(null, "O Lobo de Wall Street",
                            Genero.COMEDIA,
                            "Ascensão e queda de um corretor inescrupuloso.",
                            0.0,
                            List.of("comédia","drama"),
                            "https://upload.wikimedia.org/wikipedia/pt/thumb/8/8d/The_Wolf_of_Wall_Street.jpg/250px-The_Wolf_of_Wall_Street.jpg"),
                    new Filme(null, "A Viagem de Chihiro",
                            Genero.ANIMACAO,
                            "Um clássico da animação japonesa.",
                            0.0,
                            List.of("animação","fantasia"),
                            "https://br.web.img3.acsta.net/c_310_420/pictures/210/527/21052756_20131024195513383.jpg"),
                    new Filme(null, "O Senhor dos Anéis: A Sociedade do Anel",
                            Genero.AVENTURA,
                            "A jornada do anel — início da trilogia épica.",
                            0.0,
                            List.of("fantasia","aventura"),
                            "https://br.web.img3.acsta.net/medias/nmedia/18/92/91/32/20224832.jpg")
            );

            filmeRepository.saveAll(filmes);
            System.out.println("✅ Filmes de teste carregados no banco de dados!");
        }
    }
}
