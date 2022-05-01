package br.com.alura.spring.mvc.mudi.respository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.mvc.mudi.model.Pedido;
import br.com.alura.spring.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Query("SELECT p FROM Pedido p JOIN p.user u where u.username = :userName and p.statusPedido = :statusPedido")
	public Collection<Pedido> findByStatusPedido(StatusPedido statusPedido, String userName);
	
	@Query("SELECT p FROM Pedido p JOIN p.user u WHERE u.username = :userName")
	public Collection<Pedido> findByUser(String userName);

}
