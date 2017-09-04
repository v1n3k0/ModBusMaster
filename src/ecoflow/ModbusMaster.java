package ecoflow;

import modBus.EntradaAnalogica;
import modBus.Conexao;
import modBus.EntradaDigital;
import modBus.Solenoide;
import modBus.Registro;
import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.BitVector;

public class ModbusMaster {

    public static void main(String[] args) throws Exception {
        
        //Variavel de leitura e escrita
        int count = 8; //Varialvel contador
        int ref = 0; //Rerencia de inicio
        
        int[] valores = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}; //Escrever Multiplos registros
        int[] respostas = new int[count]; // Leitura multiplos registros
        
        byte[] byteVector = {5}; //Numeros para BitVector
        BitVector bitVectorValor = new BitVector( byteVector.length );//Inicializando BitVector
        bitVectorValor = bitVectorValor.createBitVector( byteVector );//Transforma em binarios de 8bit
        
        BitVector bitVector = new BitVector(count);//Leitura Coils Outputs
        InputRegister[] inputRegisters = new InputRegister[count];
        Register[] registers = new Register[count];
        
        //Criar a conexão
        TCPMasterConnection conexao;
        conexao = Conexao.configurar("127.0.0.1", Modbus.DEFAULT_PORT);
        
        //Escrever no registro unica vez
        //System.out.println( Registro.escrever(conexao, ref, 250) );
        
        //Escrever multiplos registros
        //System.out.println( Registro.escrever(conexao, ref, valores) );
                
        //Ler os registros
        /*respostas = Registro.ler(conexao, ref, count);
        for(int i = 0; i < count; i++){
            System.out.println( respostas[i] );
        }*/
        
        //Escrever Coil
        //System.out.println( SaidaDiscreta.escrever(conexao, ref, true) );
        
        //Escrever multiplos Coil
        //System.out.println( SaidaDiscreta.escrever(conexao, ref, bitVectorValor) );
        //System.out.println( bitVectorValor.toString() );
        
        //Ler Coil
        /*bitVector = SaidaDiscreta.ler(conexao, ref, count);
        for(int i = 0; i < count; i++){
            System.out.println(bitVector.getBit(i) );
        }*/
        
        //Ler as entrada Analogica
        inputRegisters = EntradaAnalogica.ler(conexao, ref, count);
        for(int i = 0; i < count; i++){
            System.out.println( inputRegisters[i].getValue());
        }
        
        //Ler as entradas Digitais
        /*bitVector = EntradaDiscreta.ler(conexao, ref, count);
        for(int i = 0; i < count; i++){
            System.out.println(bitVector.getBit(i) );
        }*/
        
        //Fechar conexão
        conexao.close();
        
    }
    
}
