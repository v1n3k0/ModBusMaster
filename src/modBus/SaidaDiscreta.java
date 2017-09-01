package modBus;

import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadCoilsResponse;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.msg.WriteCoilResponse;
import net.wimpi.modbus.msg.WriteMultipleCoilsRequest;
import net.wimpi.modbus.msg.WriteMultipleCoilsResponse;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.util.BitVector;

public class SaidaDiscreta {
    
    public static boolean escrever(TCPMasterConnection con, int ref, boolean valor){
        
        try {
            
             /* As instâncias importantes das classes mencionadas anteriormente */
            ModbusTCPTransaction trans = null; //A transação
            WriteCoilRequest req = null; //o pedido
            WriteCoilResponse res = null; //a resposta
            
            //3. Prepare o pedido
            req = new WriteCoilRequest(ref, valor );

            //4. Prepare a transação
            trans = new ModbusTCPTransaction(con);
            trans.setRequest(req);
            
            //5. Execute os tempos de repetição da transação
            trans.execute();
            
            //Verifica se escreveu com sucesso
            res = (WriteCoilResponse) trans.getResponse();
            if( res.getReference() == ref && res.getCoil() == valor ){
                return true;
            }else{
                System.out.println("Erro ao escrever no unico boleano! referencia ou valor não corresponde.");
                return false; 
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no unico boaleano!");
            
            return false;
        }
        
    }
    
    public static boolean escreverMultiplos(TCPMasterConnection con, int ref, BitVector valor){
        
        try {
            /* As instâncias importantes das classes mencionadas anteriormente */
            ModbusTCPTransaction trans = null; //A transação
            WriteMultipleCoilsRequest req = null; //o pedido
            WriteMultipleCoilsResponse res = null; //a resposta
            
            //3. Prepare o pedido
            req = new WriteMultipleCoilsRequest(ref, valor);
            
            //4. Prepare a transação
            trans = new ModbusTCPTransaction(con);
            trans.setRequest(req);
            
            //5. Execute os tempos de repetição da transação
            trans.execute();
            
            //Verifica se escreveu com sucesso
            res = (WriteMultipleCoilsResponse) trans.getResponse();
            if( res.getReference() == ref && res.getBitCount() == valor.size() ){
                return true;
            }else{
                System.out.println("Erro ao escrever nos multiplos boleanos! referencia ou quantidade valores não corresponde.");
                return false;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever nos multiplos boleanos!");
            
            return false;
        }
        
    }
    
    public static BitVector ler(TCPMasterConnection con, int ref, int count){
        
        try {
            
            /* As instâncias importantes das classes mencionadas anteriormente */
            ModbusTCPTransaction trans = null; //A transação
            ReadCoilsRequest req = null; //o pedido
            ReadCoilsResponse res = null; //a resposta
            
            //3. Prepare o pedido
            req = new ReadCoilsRequest(ref, count);
            
            //4. Prepare a transação
            trans = new ModbusTCPTransaction(con);
            trans.setRequest(req);
            
            //5. Execute os tempos de repetição da transação
            trans.execute();
            
            //Retorno dos valores solicitados
            res = (ReadCoilsResponse) trans.getResponse();
            
            return res.getCoils();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao ler boleano!");
            
            return null;
        }
    }
    
}
