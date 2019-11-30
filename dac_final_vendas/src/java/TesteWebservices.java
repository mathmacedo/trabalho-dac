
import com.tads.dac.beans.Cidade;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class TesteWebservices {

    public void main(String[] args) {
        Client client = ClientBuilder.newClient();
        Cidade[] c = client
                .target("http://localhost:8080/dac_final_gerencial/webresources/cidade")
                .request(MediaType.APPLICATION_JSON)
                .get(Cidade[].class);
        List<Cidade> cidadeList = Arrays.asList(c);
    }
    
}
