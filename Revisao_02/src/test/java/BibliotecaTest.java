import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class BibliotecaTest {
    private static List<String> livros;

    @BeforeAll
    public static void prepararBiblioteca() {
        livros = new ArrayList<>();

        livros.add("Dom Casmurro");
        livros.add("O Pequeno Príncipe");
        livros.add("1984");

        System.out.println("Biblioteca preparada");
    }

    @Test
    public void deveVerificarSePossuiOsLivros(){
        assertEquals(3,livros.size());
    }
    @Test
    public void deveVerificarLivroEspecifico(){
        assertTrue(livros.contains("1984"));
    }
    @Test
    public void naoDeveConterLivroInexistente(){
        assertFalse(livros.contains("Livro inexistente"));
    }
    @AfterAll
    public static void encerrarBiblioteca(){
        livros.clear();
        System.out.println("Biblioteca encerrada");
    }
}