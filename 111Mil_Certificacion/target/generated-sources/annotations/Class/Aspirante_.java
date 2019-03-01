package Class;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Aspirante.class)
public abstract class Aspirante_ {

	public static volatile SingularAttribute<Aspirante, Date> fechaDeNac;
	public static volatile SingularAttribute<Aspirante, EntidadEducativa> entidadEducativa;
	public static volatile SingularAttribute<Aspirante, Integer> idAspirante;
	public static volatile SingularAttribute<Aspirante, String> apellido;
	public static volatile ListAttribute<Aspirante, Inscripcion> inscripciones;
	public static volatile SingularAttribute<Aspirante, String> direccion;
	public static volatile SingularAttribute<Aspirante, Integer> sexo;
	public static volatile SingularAttribute<Aspirante, String> nombre;
	public static volatile SingularAttribute<Aspirante, Integer> dni;

}

