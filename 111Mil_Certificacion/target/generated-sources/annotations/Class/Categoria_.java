package Class;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Categoria.class)
public abstract class Categoria_ {

	public static volatile ListAttribute<Categoria, Inscripcion> inscripciones;
	public static volatile SingularAttribute<Categoria, Integer> limiteSuperiorEdad;
	public static volatile SingularAttribute<Categoria, Integer> idCategoria;
	public static volatile SingularAttribute<Categoria, Integer> sexo;
	public static volatile SingularAttribute<Categoria, String> nombre;
	public static volatile SingularAttribute<Categoria, Integer> limiteInferiorEdad;

}

