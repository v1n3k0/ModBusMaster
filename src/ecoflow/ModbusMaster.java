package ecoflow;

import modBus.EntradaRegistro;
import modBus.Conexao;
import modBus.Boleano;
import modBus.Registro;
import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.util.BitVector;

public class ModbusMaster {

    public static void main(String[] args) throws Exception {
        
        //Variavel de leitura e escrita
        int count = 8; //Varialvel contador
        int ref = 8; //Rerencia de inicio
        int[] valores = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}; //Escrever Multiplos registros
        int[] respostas = new int[count]; // Leitura multiplos registros
        BitVector bitVector = new BitVector(count);//Leitura Coils Outputs
        byte[] byteVector = {1}; //Numeros para BitVector
        BitVector bitVectorValor = new BitVector( byteVector.length );//Inicializando BitVector
        bitVectorValor = bitVectorValor.createBitVector( byteVector );//Transforma em binarios de 8bit
        
        //Instanciar a classe
        Registro registros = new Registro();
        EntradaRegistro entradaRegistro = new EntradaRegistro();
        Boleano boleano = new Boleano();
        
        //Criar a conexão
        TCPMasterConnection conexao;
        conexao = Conexao.Connection("127.0.0.1", Modbus.DEFAULT_PORT);
        
        //Escrever no registro unica vez
        //System.out.println( registros.escreverUnicoRegistro(conexao, ref, 250) );
        
        //Escrever multiplos registros
        //System.out.println( registros.escreverMultiplosRegistros(conexao, ref, valores) );
                
        //Ler os registros
        /*respostas = registros.lerMultiplosRegistros(conexao, ref, count);
        for(int i = 0; i < count; i++){
            System.out.println( respostas[i] );
        }*/
        
        //Ler os entrada registro
        /*respostas = entradaRegistro.lerEntradaRegistros(conexao, ref, count);
        for(int i = 0; i < count; i++){
            System.out.println( respostas[i] );
        }*/
        
        //Escrever boelano
        //System.out.println( boleano.escreverBoleano(conexao, ref, false) );
        
        //Escrever multiplos boleanos
        System.out.println( boleano.escreverMultiplosBoleanos(conexao, ref, bitVectorValor) );
        System.out.println( bitVectorValor.toString() );
        
        //Ler boleanos
        bitVector = boleano.lerBoleano(conexao, ref, count);
        for(int i = 0; i < count; i++){
            System.out.println(bitVector.getBit(i) );
        }
        
        //Fechar conexão
        conexao.close();
        
    }
    
}
