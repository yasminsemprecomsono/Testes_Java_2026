import org.example.ContaBancaria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ContaBancariaTest {

    private ContaBancaria conta;

    @BeforeEach
    void setUp(){
        conta = new ContaBancaria("Lucas",100.0);
    }

    @Test
    public void deveVerSaldoInicial(){
        assertEquals(100.0,conta.getSaldo(),0.001);
    }
    @Test
    public void deveDepositarNaConta(){
        conta.depositar(50.0);

        assertEquals(150.0,conta.getSaldo(),0.001);
    }
    @Test
    public void deveSacarDaconta(){
        conta.sacar(30.0);
        assertEquals(70.0,conta.getSaldo(),0.001);
    }
    @Test
    public void deveTentarSacar200(){
        assertThrows(IllegalArgumentException.class,()->{
            conta.sacar(200.0);
        });
        assertEquals(100.0,conta.getSaldo(),0.001);
    }
}