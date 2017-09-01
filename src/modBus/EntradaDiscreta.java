package modBus;

import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesResponse;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.util.BitVector;

public class EntradaDiscreta {
    
    public static BitVector ler(TCPMasterConnection con, int ref, int count){
        
        //Vetor de resposta
        int[] valores = new int[count];
        
        try {            
            /* As instâncias importantes das classes mencionadas anteriormente */
            ModbusTCPTransaction trans = null; //A transação
            ReadInputDiscretesRequest req = null; //o pedido
            ReadInputDiscretesResponse res = null; //a resposta
            
            //3. Prepare o pedido
            req = new ReadInputDiscretesRequest(ref, count);
            
            //4. Prepare a transação
            trans = new ModbusTCPTransaction(con);
            trans.setRequest(req);
            
            //5. Execute os tempos de repetição da transação
            trans.execute();
            
            //Retorno dos valores solicitados
            res = (ReadInputDiscretesResponse) trans.getResponse();
            
            return res.getDiscretes();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao entradas discretas!");
            
            return null;
        }
        
    }
    
}
