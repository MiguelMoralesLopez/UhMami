package uhmami.modelo.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="reservas")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	private Integer comensales;
	private String observaciones;
	@ManyToOne
	@JoinColumn(name="ID_usuario")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name="ID_cliente")
	private Cliente cliente;
	@ManyToMany
	@JoinTable(
		name="mesa_con_reserva"
		, joinColumns={
			@JoinColumn(name="ID_reserva")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_mesa")
			}
		)
	private List<Mesa> mesa;

}
