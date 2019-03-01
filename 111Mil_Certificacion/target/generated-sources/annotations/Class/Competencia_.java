package Class;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Competencia.class)
public abstract class Competencia_ {

	public static volatile ListAttribute<Competencia, Inscripcion> inscripciones;
	public static volatile SingularAttribute<Competencia, String> nombre;
	public static volatile SingularAttribute<Competencia, Integer> idCompetencia;

}

