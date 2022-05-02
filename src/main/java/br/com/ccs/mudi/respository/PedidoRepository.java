package br.com.ccs.mudi.respository;

import br.com.ccs.mudi.model.Pedido;
import br.com.ccs.mudi.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p JOIN p.user u where u.username = :userName and p.statusPedido = :statusPedido")
    Collection<Pedido> findByStatusPedido(StatusPedido statusPedido, String userName);

    @Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :userName")
    Collection<Pedido> findByUser(String userName);

}
