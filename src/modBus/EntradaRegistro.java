package modBus;

import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.net.TCPMasterConnection;

public class EntradaRegistro {
    
    public int[] lerEntradaRegistros(TCPMasterConnection con, int ref, int count){
        
        //Vetor de resposta
        int[] valores = new int[count];
        
        try {            
            /* As instâncias importantes das classes mencionadas anteriormente */
            ModbusTCPTransaction trans = null; //A transação
            ReadInputRegistersRequest req = null; //o pedido
            ReadInputRegistersResponse res = null; //a resposta
            
            //3. Prepare o pedido
            req = new ReadInputRegistersRequest(ref, count);
            
            //4. Prepare a transação
            trans = new ModbusTCPTransaction(con);
            trans.setRequest(req);
            
            //5. Execute os tempos de repetição da transação
            trans.execute();
            
            //Retorno dos valores solicitados
            int k = 0;
            res = (ReadInputRegistersResponse) trans.getResponse();
            do {
                valores[k] = res.getRegisterValue(k);
                k++;
            } while (k < count);
            
            return valores;
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao ler multiplos entrada de registro!");
            
            return null;
        }
        
    }
    
}
