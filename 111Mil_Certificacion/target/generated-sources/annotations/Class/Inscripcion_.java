package Class;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Inscripcion.class)
public abstract class Inscripcion_ {

	public static volatile SingularAttribute<Inscripcion, Date> fecha;
	public static volatile SingularAttribute<Inscripcion, Estado> estado;
	public static volatile SingularAttribute<Inscripcion, Aspirante> aspirante;
	public static volatile SingularAttribute<Inscripcion, Categoria> categoria;
	public static volatile SingularAttribute<Inscripcion, Integer> idInscripcion;
	public static volatile SingularAttribute<Inscripcion, Competencia> competencia;

}

