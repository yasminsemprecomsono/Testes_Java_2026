import org.example.ProdutoEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoEntityTest {

    @ParameterizedTest(name = "Preço inválido: {0}")
    @ValueSource(doubles = {0.0, -0.01, -10.0, -100.0})
    @DisplayName("Deve lançar exceção ao instanciar produto com preço menor ou igual a zero")
    void precoInvalidoNoConstrutorDeveLancarExcecao(double precoInvalido) {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ProdutoEntity("Teclado", precoInvalido, 10)
        );
        assertEquals("O preço deve ser maior que zero", excecao.getMessage());
    }

    @ParameterizedTest(name = "Quantidade invalida: {0}")
    @ValueSource(ints = {-1, -5, -50})
    @DisplayName("Deve lançar exceção ao instanciar produto com quantidade negativo")
    void quantidadeNegativoNoConstrutorDeveLancarExcecao(int quantidadeInvalida){
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ProdutoEntity("Mouse", 50, quantidadeInvalida)
        );
        assertEquals("A quantidade deve ser acima de zero", excecao.getMessage());
    }

    @ParameterizedTest(name = "Quantidade Inválida: {0}")
    @ValueSource(doubles = {-1.0, -1.01, 100.01, 150.0})
    @DisplayName("Deve lançar exceção para percentual de desconto fora de faixa 0 a 100")
    void percentualDescontoInvalidoLancaExcecao(double percentualInvalido) {
        ProdutoEntity produto = new ProdutoEntity("Monitor", 1000.0, 5);
                IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
            () -> produto.calcularPrecoComDesconto(percentualInvalido)
        );
        assertEquals("O percentual de desconto deve estar entre 0 e 100", excecao.getMessage());
    }
    @ParameterizedTest(name = "Caso {index}: R$ {0} x {1} un. = Estoque R$ {2}")
    @CsvSource({
            "100.00,  2, 200.00",
            "50.00,  10, 500.00",
            "19.90,   3,  59.70",
            "150.00,  0,   0.00"
    })
    @DisplayName("Deve calcular o valor total do estoque corretamente")
    void calcularValorEstoqueDeveRetornarValorCorreto(double preco, int quantidade, double valorEsperado) {
        ProdutoEntity produto = new ProdutoEntity("Produto Teste", preco, quantidade);

        double valorObtido = produto.calcularValorEstoque();

        assertEquals(valorEsperado, valorObtido, 0.0001);
    }

    @ParameterizedTest(name = "Caso {index}: Preço R$ {0} com {1}% desc. = R$ {2}")
    @CsvSource({
            "100.00,   0.0, 100.00",
            "100.00,  10.0,  90.00",
            "200.00,  25.0, 150.00",
            "500.00,  50.0, 250.00",
            "80.00,  100.0,   0.00"
    })
    @DisplayName("Deve calcular o preço final com desconto corretamente")
    void calcularPrecoComDescontoDeveRetornarPrecoComDesconto(double precoOriginal, double percentual, double precoEsperado) {
        ProdutoEntity produto = new ProdutoEntity("Produto Teste", precoOriginal, 1);

        double precoObtido = produto.calcularPrecoComDesconto(percentual);

        assertEquals(precoEsperado, precoObtido, 0.0001);
    }
}



