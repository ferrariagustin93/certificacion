package Class;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estado.class)
public abstract class Estado_ {

	public static volatile SingularAttribute<Estado, Integer> idEstado;
	public static volatile SingularAttribute<Estado, String> denominacion;
	public static volatile ListAttribute<Estado, Inscripcion> inscripciones;

}

