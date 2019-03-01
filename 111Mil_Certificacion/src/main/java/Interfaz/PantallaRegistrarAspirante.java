/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Class.Aspirante;
import Class.Categoria;
import Class.Competencia;
import Class.EntidadEducativa;
import Class.Inscripcion;
import Controller.GestorRegistrarAspirante;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Agustin
 */
public class PantallaRegistrarAspirante extends javax.swing.JFrame {
    private final GestorRegistrarAspirante gestorRegistrarAspirante;
    private final TablaCompetenciaModel tablaCompetenciaModel;
    private final ListaEntidadEducativaModel listaEntidadEducativaModel;
    private final ListaAspiranteModel listaAspiranteModel;
    private final ListaCategoriaModel listaCategoriaModel;
    private List<Aspirante> registroAspirantesNuevos;
    private final List<Inscripcion> inscripciones;
    private final EntidadEducativaListModelListener entidadEducativaListModelListener;
    private final AspiranteListModelListener aspiranteListModelListener;
    private final CompetenciaTablaModelListener competenciaTablaModelListener;
    private final CategoriaListaModelListener categoriaListaModelListener;
    
    /**
     * Creates new form Principal
     */
    public PantallaRegistrarAspirante(GestorRegistrarAspirante gestorRegistrarAspirante) {
        this.registroAspirantesNuevos = new ArrayList();
        this.inscripciones = new ArrayList();
        this.gestorRegistrarAspirante = gestorRegistrarAspirante;
        this.listaEntidadEducativaModel = new ListaEntidadEducativaModel(this.gestorRegistrarAspirante.buscarEntidadesEducativas());
        this.listaAspiranteModel = new ListaAspiranteModel();
        this.tablaCompetenciaModel = new TablaCompetenciaModel(this.gestorRegistrarAspirante.buscarCompetencias());
        this.listaCategoriaModel = new ListaCategoriaModel(this.gestorRegistrarAspirante.buscarCategorias());
        
        // create object listener
        this.entidadEducativaListModelListener = new EntidadEducativaListModelListener(this);
        this.aspiranteListModelListener = new AspiranteListModelListener(this);
        this.competenciaTablaModelListener = new CompetenciaTablaModelListener(this);
        this.categoriaListaModelListener = new CategoriaListaModelListener(this);
        initComponents();
        
        // add listener
        this.jListEntidadEducativa.getSelectionModel().addListSelectionListener(this.entidadEducativaListModelListener);
        this.jListAspirantes.getSelectionModel().addListSelectionListener(this.aspiranteListModelListener);
        this.jTableCompetencias.getSelectionModel().addListSelectionListener(this.competenciaTablaModelListener);
        this.jListCategoria.getSelectionModel().addListSelectionListener(this.categoriaListaModelListener);
        
        
        // centrar frame
        this.setLocationRelativeTo(null);
        
    }
    
    /**
     * En el caso de haber seleccionado una fila de la lista de Entidades 
     * Educativas, devuelve la entidad seleccionada.
     * @return La entidad seleccionada de la lista de entidades educativas.
     */
    public EntidadEducativa obtenerEntidadEducativaSeleccionada()
    {
        EntidadEducativa retorno = null;
        int filaSeleccionada = jListEntidadEducativa.getSelectedIndex();
        
        // validamos que haya una fila seleccionada
        if (filaSeleccionada >= 0) {
            retorno = (EntidadEducativa)this.listaEntidadEducativaModel.getElementAt(filaSeleccionada);
        }
        
        return retorno;
    }
    
    public Aspirante obtenerAspiranteSeleccionado()
    {
        Aspirante retorno = null;
        int filaSeleccionada = jListAspirantes.getSelectedIndex();
        
        // validamos que haya una fila seleccionada
        if (filaSeleccionada >= 0) {
            retorno = (Aspirante)this.listaAspiranteModel.getElementAt(filaSeleccionada);
        }
        
        return retorno;
    }
    
    public Competencia obtenerCompetenciaSeleccionada()
    {
        Competencia retorno = null;
        int filaSeleccionada = jTableCompetencias.getSelectedRow();
        
        // validamos que haya una fila seleccionada
        if (filaSeleccionada >= 0) {
            retorno = (Competencia)this.tablaCompetenciaModel.obtenerCompetenciaEn(filaSeleccionada);
        }
        
        return retorno;
    }
    
    public Categoria obtenerCategoriaSeleccionada()
    {
        Categoria retorno = null;
        int filaSeleccionada = jListCategoria.getSelectedIndex();
        
        // validamos que haya una fila seleccionada
        if (filaSeleccionada >= 0) {
            retorno = (Categoria)this.listaCategoriaModel.obtenerCategoriaEn(filaSeleccionada);
        }
        
        return retorno;
    }
    
    public ListaAspiranteModel getListAspiranteModel()
    {
        return this.listaAspiranteModel;
    }
    
    public ListaCategoriaModel getListaCategoriaModel()
    {
        return this.listaCategoriaModel;
    }
    
    public List<Aspirante> getRegistroAspirantesNuevos()
    {
        return this.registroAspirantesNuevos;
    }
    
    public TablaCompetenciaModel getTablaCompetenciaModel()
    {
        return this.tablaCompetenciaModel;
    }
    
    public JList getJListCategoria()
    {
        return this.jListCategoria;
    }
    
    public JTable getJTableCompetencia()
    {
        return jTableCompetencias;
    }
    
    public JList getJListAspirante()
    {
        return this.jListAspirantes;
    }
    
    public GestorRegistrarAspirante getGestorRegistrarAspirante()
    {
        return this.gestorRegistrarAspirante;
    }
    
    public List<Inscripcion> getInscripciones()
    {
        return this.inscripciones;
    }
    
    public  boolean esAspiranteNuevo(Aspirante aspirante)
    {
        Iterator i = this.registroAspirantesNuevos.iterator();
        while(i.hasNext())
        {
            Aspirante aux = (Aspirante) i.next();
            if(aux.equals(aspirante))
                return true;
        }
        return false;
    }
    
    public void clearCompetenciaCategoria()
    {
        // limpiar selecciones y tablas
        this.tablaCompetenciaModel.clearTable();
        this.jTableCompetencias.clearSelection();
        this.jListCategoria.clearSelection();
        this.tablaCompetenciaModel.fireTableStructureChanged();
    }
    
    public void agregarInscripcion(Inscripcion i)
    {
        this.inscripciones.add(i);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogNuevoAspirante = new javax.swing.JDialog();
        jPanelFondo1 = new javax.swing.JPanel();
        jPanelBarraTarea1 = new javax.swing.JPanel();
        jButtonMin1 = new javax.swing.JButton();
        jButtonMax1 = new javax.swing.JButton();
        jButtonExit1 = new javax.swing.JButton();
        jLabelTituloNuevoAspirante = new javax.swing.JLabel();
        jPanelIterior1 = new javax.swing.JPanel();
        jLabelDialogNombre = new javax.swing.JLabel();
        jLabelDialogApellido = new javax.swing.JLabel();
        jLabelDialogDireccion = new javax.swing.JLabel();
        jLabelDialogFechaDeNacimiento = new javax.swing.JLabel();
        jLabelDialogSexo = new javax.swing.JLabel();
        jLabelDialogDNI = new javax.swing.JLabel();
        jButtonDialogCancelar = new javax.swing.JButton();
        jButtonDialogAceptar = new javax.swing.JButton();
        jTextFieldDialogNombre = new javax.swing.JTextField();
        jTextFieldDialogApellido = new javax.swing.JTextField();
        jTextFieldDialogDireccion = new javax.swing.JTextField();
        jTextFieldDialogDNI = new javax.swing.JTextField();
        jRadioButtonFemenino = new javax.swing.JRadioButton();
        jRadioButtonMasculino = new javax.swing.JRadioButton();
        jDateChooserFechaDeNacimiento = new com.toedter.calendar.JDateChooser();
        jPanelFondo = new javax.swing.JPanel();
        jPanelBarraTarea = new javax.swing.JPanel();
        jButtonMin = new javax.swing.JButton();
        jButtonMax = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jLabelLogo = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelIterior = new javax.swing.JPanel();
        jPanelEntidadEducativa = new javax.swing.JPanel();
        jScrollPaneEntidadEducativa = new javax.swing.JScrollPane();
        jListEntidadEducativa = new javax.swing.JList();
        jPanelAspirantes = new javax.swing.JPanel();
        jScrollPaneAspirantes = new javax.swing.JScrollPane();
        jListAspirantes = new javax.swing.JList();
        jButtonNuevoAspirante = new javax.swing.JButton();
        jPanelCompetencias = new javax.swing.JPanel();
        jScrollPaneCompetencias = new javax.swing.JScrollPane();
        jTableCompetencias = new javax.swing.JTable();
        jPanelCategorias = new javax.swing.JPanel();
        jScrollPaneCategoria = new javax.swing.JScrollPane();
        jListCategoria = new javax.swing.JList();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        jDialogNuevoAspirante.setMinimumSize(new java.awt.Dimension(640, 359));
        jDialogNuevoAspirante.setModal(true);
        jDialogNuevoAspirante.setUndecorated(true);
        jDialogNuevoAspirante.setResizable(false);

        jPanelFondo1.setBackground(new java.awt.Color(0, 51, 102));
        jPanelFondo1.setPreferredSize(new java.awt.Dimension(900, 600));

        jPanelBarraTarea1.setBackground(new java.awt.Color(0, 51, 102));

        jButtonMin1.setBorder(null);
        jButtonMin1.setIcon(new javax.swing.ImageIcon(getClass().getResource("../res/minimizeOriginal.png")));
        jButtonMin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMin1ActionPerformed(evt);
            }
        });

        jButtonMax1.setIcon(new javax.swing.ImageIcon(getClass().getResource("../res/maximizeOriginal.png")));
        jButtonMax1.setBorder(null);
        jButtonMax1.setBorderPainted(false);
        jButtonMax1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMax1ActionPerformed(evt);
            }
        });

        jButtonExit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("../res/iconExitOriginal.png"))); // NOI18N
        jButtonExit1.setBorder(null);
        jButtonExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExit1ActionPerformed(evt);
            }
        });

        jLabelTituloNuevoAspirante.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelTituloNuevoAspirante.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloNuevoAspirante.setText("Nuevo Aspirante");

        javax.swing.GroupLayout jPanelBarraTarea1Layout = new javax.swing.GroupLayout(jPanelBarraTarea1);
        jPanelBarraTarea1.setLayout(jPanelBarraTarea1Layout);
        jPanelBarraTarea1Layout.setHorizontalGroup(
            jPanelBarraTarea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBarraTarea1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabelTituloNuevoAspirante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 678, Short.MAX_VALUE)
                .addComponent(jButtonMin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonMax1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExit1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBarraTarea1Layout.setVerticalGroup(
            jPanelBarraTarea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBarraTarea1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBarraTarea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBarraTarea1Layout.createSequentialGroup()
                        .addGroup(jPanelBarraTarea1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonExit1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonMax1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonMin1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabelTituloNuevoAspirante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabelDialogNombre.setText("Nombre");

        jLabelDialogApellido.setText("Apellido");

        jLabelDialogDireccion.setText("Dirección");

        jLabelDialogFechaDeNacimiento.setText("Fecha de nacimiento");

        jLabelDialogSexo.setText("Sexo");

        jLabelDialogDNI.setText("DNI");

        jButtonDialogCancelar.setText("Cancelar");
        jButtonDialogCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDialogCancelarActionPerformed(evt);
            }
        });

        jButtonDialogAceptar.setText("Aceptar");
        jButtonDialogAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDialogAceptarActionPerformed(evt);
            }
        });

        jTextFieldDialogDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDialogDNIActionPerformed(evt);
            }
        });

        jRadioButtonFemenino.setText("Femenino");

        jRadioButtonMasculino.setText("Masculino");

        jDateChooserFechaDeNacimiento.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooserFechaDeNacimiento.setMaxSelectableDate(new java.util.Date(253370779263000L));
        jDateChooserFechaDeNacimiento.setMinSelectableDate(new java.util.Date(-2208969729000L));
        Date date = new Date();
        jDateChooserFechaDeNacimiento.setMaxSelectableDate(date);

        javax.swing.GroupLayout jPanelIterior1Layout = new javax.swing.GroupLayout(jPanelIterior1);
        jPanelIterior1.setLayout(jPanelIterior1Layout);
        jPanelIterior1Layout.setHorizontalGroup(
            jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIterior1Layout.createSequentialGroup()
                .addGroup(jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelIterior1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelIterior1Layout.createSequentialGroup()
                                .addComponent(jLabelDialogApellido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldDialogApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelIterior1Layout.createSequentialGroup()
                                .addComponent(jLabelDialogNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldDialogNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelIterior1Layout.createSequentialGroup()
                                .addComponent(jLabelDialogDireccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldDialogDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelIterior1Layout.createSequentialGroup()
                                .addGroup(jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelDialogDNI)
                                    .addComponent(jLabelDialogSexo)
                                    .addComponent(jLabelDialogFechaDeNacimiento))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                                .addGroup(jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldDialogDNI, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                                    .addGroup(jPanelIterior1Layout.createSequentialGroup()
                                        .addComponent(jRadioButtonFemenino)
                                        .addGap(30, 30, 30)
                                        .addComponent(jRadioButtonMasculino))
                                    .addComponent(jDateChooserFechaDeNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanelIterior1Layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(jButtonDialogAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDialogCancelar)))
                .addGap(43, 43, 43))
        );
        jPanelIterior1Layout.setVerticalGroup(
            jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIterior1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDialogNombre)
                    .addComponent(jTextFieldDialogNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDialogApellido)
                    .addComponent(jTextFieldDialogApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDialogDireccion)
                    .addComponent(jTextFieldDialogDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDialogFechaDeNacimiento)
                    .addComponent(jDateChooserFechaDeNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDialogSexo)
                    .addComponent(jRadioButtonFemenino)
                    .addComponent(jRadioButtonMasculino))
                .addGap(18, 18, 18)
                .addGroup(jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDialogDNI)
                    .addComponent(jTextFieldDialogDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanelIterior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDialogCancelar)
                    .addComponent(jButtonDialogAceptar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelFondo1Layout = new javax.swing.GroupLayout(jPanelFondo1);
        jPanelFondo1.setLayout(jPanelFondo1Layout);
        jPanelFondo1Layout.setHorizontalGroup(
            jPanelFondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBarraTarea1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelFondo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelIterior1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelFondo1Layout.setVerticalGroup(
            jPanelFondo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFondo1Layout.createSequentialGroup()
                .addComponent(jPanelBarraTarea1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelIterior1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialogNuevoAspiranteLayout = new javax.swing.GroupLayout(jDialogNuevoAspirante.getContentPane());
        jDialogNuevoAspirante.getContentPane().setLayout(jDialogNuevoAspiranteLayout);
        jDialogNuevoAspiranteLayout.setHorizontalGroup(
            jDialogNuevoAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );
        jDialogNuevoAspiranteLayout.setVerticalGroup(
            jDialogNuevoAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        jPanelFondo.setBackground(new java.awt.Color(0, 51, 102));
        jPanelFondo.setPreferredSize(new java.awt.Dimension(900, 600));

        jPanelBarraTarea.setBackground(new java.awt.Color(0, 51, 102));

        jButtonMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("../res/minimizeOriginal.png"))); // NOI18N
        jButtonMin.setBorder(null);
        jButtonMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinActionPerformed(evt);
            }
        });

        jButtonMax.setIcon(new javax.swing.ImageIcon(getClass().getResource("../res/maximizeOriginal.png"))); // NOI18N
        jButtonMax.setBorder(null);
        jButtonMax.setBorderPainted(false);
        jButtonMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMaxActionPerformed(evt);
            }
        });

        jButtonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("../res/iconExitOriginal.png"))); // NOI18N
        jButtonExit.setBorder(null);
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("../res/javaIcon.png"))); // NOI18N

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setText("Registrar Aspirantes");

        javax.swing.GroupLayout jPanelBarraTareaLayout = new javax.swing.GroupLayout(jPanelBarraTarea);
        jPanelBarraTarea.setLayout(jPanelBarraTareaLayout);
        jPanelBarraTareaLayout.setHorizontalGroup(
            jPanelBarraTareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBarraTareaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 679, Short.MAX_VALUE)
                .addComponent(jButtonMin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonMax, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelBarraTareaLayout.setVerticalGroup(
            jPanelBarraTareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBarraTareaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBarraTareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMax, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonMin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelBarraTareaLayout.createSequentialGroup()
                .addGroup(jPanelBarraTareaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelBarraTareaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanelEntidadEducativa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanelEntidadEducativa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1,1,1,1,Color.GRAY),"Seleccione una Entidad Educativa...",TitledBorder.LEFT,TitledBorder.TOP,new Font("Arial",Font.BOLD,11)));

        jListEntidadEducativa.setModel(listaEntidadEducativaModel);
        jListEntidadEducativa.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListEntidadEducativa.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListEntidadEducativaValueChanged(evt);
            }
        });
        jScrollPaneEntidadEducativa.setViewportView(jListEntidadEducativa);

        javax.swing.GroupLayout jPanelEntidadEducativaLayout = new javax.swing.GroupLayout(jPanelEntidadEducativa);
        jPanelEntidadEducativa.setLayout(jPanelEntidadEducativaLayout);
        jPanelEntidadEducativaLayout.setHorizontalGroup(
            jPanelEntidadEducativaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 858, Short.MAX_VALUE)
            .addGroup(jPanelEntidadEducativaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPaneEntidadEducativa, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE))
        );
        jPanelEntidadEducativaLayout.setVerticalGroup(
            jPanelEntidadEducativaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
            .addGroup(jPanelEntidadEducativaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEntidadEducativaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPaneEntidadEducativa, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)))
        );

        jPanelAspirantes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanelAspirantes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1,1,1,1,Color.GRAY),"Aspirantes",TitledBorder.LEFT,TitledBorder.TOP,new Font("Arial",Font.BOLD,11)));
        jPanelAspirantes.setPreferredSize(new java.awt.Dimension(460, 250));

        jListAspirantes.setModel(listaAspiranteModel);
        jListAspirantes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListAspirantes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListAspirantesValueChanged(evt);
            }
        });
        jScrollPaneAspirantes.setViewportView(jListAspirantes);

        jButtonNuevoAspirante.setText("Nuevo Aspirante");
        jButtonNuevoAspirante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoAspiranteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAspirantesLayout = new javax.swing.GroupLayout(jPanelAspirantes);
        jPanelAspirantes.setLayout(jPanelAspirantesLayout);
        jPanelAspirantesLayout.setHorizontalGroup(
            jPanelAspirantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAspirantesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAspirantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneAspirantes)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAspirantesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonNuevoAspirante, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelAspirantesLayout.setVerticalGroup(
            jPanelAspirantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAspirantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneAspirantes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonNuevoAspirante)
                .addContainerGap())
        );

        jPanelCompetencias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanelCompetencias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1,1,1,1,Color.GRAY),"Competencias",TitledBorder.LEFT,TitledBorder.TOP,new Font("Arial",Font.BOLD,11)));
        jPanelCompetencias.setMinimumSize(new java.awt.Dimension(100, 120));

        jTableCompetencias.setModel(this.tablaCompetenciaModel);
        jTableCompetencias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableCompetencias.getTableHeader().setReorderingAllowed(false);
        jScrollPaneCompetencias.setViewportView(jTableCompetencias);

        javax.swing.GroupLayout jPanelCompetenciasLayout = new javax.swing.GroupLayout(jPanelCompetencias);
        jPanelCompetencias.setLayout(jPanelCompetenciasLayout);
        jPanelCompetenciasLayout.setHorizontalGroup(
            jPanelCompetenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneCompetencias, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanelCompetenciasLayout.setVerticalGroup(
            jPanelCompetenciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneCompetencias, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );

        jPanelCategorias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanelCategorias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1,1,1,1,Color.GRAY),"Categorias",TitledBorder.LEFT,TitledBorder.TOP,new Font("Arial",Font.BOLD,11)));
        jPanelCategorias.setPreferredSize(new java.awt.Dimension(0, 120));

        jListCategoria.setModel(this.listaCategoriaModel);
        jListCategoria.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListCategoria.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListCategoriaValueChanged(evt);
            }
        });
        jScrollPaneCategoria.setViewportView(jListCategoria);

        javax.swing.GroupLayout jPanelCategoriasLayout = new javax.swing.GroupLayout(jPanelCategorias);
        jPanelCategorias.setLayout(jPanelCategoriasLayout);
        jPanelCategoriasLayout.setHorizontalGroup(
            jPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPaneCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE))
        );
        jPanelCategoriasLayout.setVerticalGroup(
            jPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
            .addGroup(jPanelCategoriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelCategoriasLayout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(jScrollPaneCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addGap(6, 6, 6)))
        );

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelIteriorLayout = new javax.swing.GroupLayout(jPanelIterior);
        jPanelIterior.setLayout(jPanelIteriorLayout);
        jPanelIteriorLayout.setHorizontalGroup(
            jPanelIteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIteriorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelIteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelIteriorLayout.createSequentialGroup()
                        .addComponent(jPanelEntidadEducativa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelIteriorLayout.createSequentialGroup()
                        .addGroup(jPanelIteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonAceptar)
                            .addComponent(jPanelAspirantes, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelIteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelIteriorLayout.createSequentialGroup()
                                .addComponent(jButtonCancelar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPanelCompetencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelCategorias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)))))
        );
        jPanelIteriorLayout.setVerticalGroup(
            jPanelIteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIteriorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelEntidadEducativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelIteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelIteriorLayout.createSequentialGroup()
                        .addComponent(jPanelCompetencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelAspirantes, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelIteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonAceptar))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelFondoLayout = new javax.swing.GroupLayout(jPanelFondo);
        jPanelFondo.setLayout(jPanelFondoLayout);
        jPanelFondoLayout.setHorizontalGroup(
            jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBarraTarea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelIterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelFondoLayout.setVerticalGroup(
            jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFondoLayout.createSequentialGroup()
                .addComponent(jPanelBarraTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelIterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMaxActionPerformed
        
        if(this.getExtendedState()==0)
            this.setExtendedState(MAXIMIZED_BOTH);
        else
            this.setExtendedState(this.NORMAL);
    }//GEN-LAST:event_jButtonMaxActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        //int op = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro que desea salir?", "Salir", JOptionPane.QUESTION_MESSAGE);
        int op = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro que desea salir?", "Salir", WIDTH, JOptionPane.QUESTION_MESSAGE);
        if(op==0)
            System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMinActionPerformed
        
        if(this.getState()==this.NORMAL)
            this.setState(this.ICONIFIED);
        else
            this.setState(this.NORMAL);
    }//GEN-LAST:event_jButtonMinActionPerformed

    private void jButtonMin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonMin1ActionPerformed

    private void jButtonMax1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMax1ActionPerformed
        // Maximizar JDialog
    }//GEN-LAST:event_jButtonMax1ActionPerformed

    private void jButtonExit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExit1ActionPerformed
        this.jDialogNuevoAspirante.dispose();
    }//GEN-LAST:event_jButtonExit1ActionPerformed

    private void jButtonDialogCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDialogCancelarActionPerformed
        limpiarFormularioAspirante();
        this.jDialogNuevoAspirante.dispose();
    }//GEN-LAST:event_jButtonDialogCancelarActionPerformed

    private void jButtonNuevoAspiranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoAspiranteActionPerformed
        EntidadEducativa seleccionada = this.obtenerEntidadEducativaSeleccionada();
        if(seleccionada!=null)
        {
            this.jDialogNuevoAspirante.setLocationRelativeTo(null);
            this.jDialogNuevoAspirante.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una Entidad Educativa", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_jButtonNuevoAspiranteActionPerformed

    private void jTextFieldDialogDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDialogDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDialogDNIActionPerformed

    private void jButtonDialogAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDialogAceptarActionPerformed
        if(controlFormulario())
        {
            Aspirante nuevoAspirante = new Aspirante();
            try
            {
                nuevoAspirante.setNombre(jTextFieldDialogNombre.getText());
                nuevoAspirante.setApellido(jTextFieldDialogApellido.getText());
                nuevoAspirante.setDireccion(jTextFieldDialogDireccion.getText());
                nuevoAspirante.setFechaDeNac(jDateChooserFechaDeNacimiento.getDate());
                if(jRadioButtonMasculino.isSelected())
                    nuevoAspirante.setSexo(1);
                if(jRadioButtonFemenino.isSelected())
                    nuevoAspirante.setSexo(2);
                nuevoAspirante.setDni(Integer.valueOf(jTextFieldDialogDNI.getText()));
                nuevoAspirante.setEntidadEducativa(this.obtenerEntidadEducativaSeleccionada());
                
                // calculo de id con fecha y hora de hoy
                int pass = new Date().hashCode();
                System.out.println("pass:"+pass+"------------");
                nuevoAspirante.setIdAspirante(pass);
                cargarNuevoAspirante(nuevoAspirante);
                limpiarFormularioAspirante();
                this.entidadEducativaListModelListener.recargar();
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(rootPane,"Error al cargar el Aspirante", "Error", JOptionPane.ERROR_MESSAGE);
            }
            this.jDialogNuevoAspirante.dispose();
        }
    }//GEN-LAST:event_jButtonDialogAceptarActionPerformed

    private void jListAspirantesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListAspirantesValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jListAspirantesValueChanged

    private void jListEntidadEducativaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListEntidadEducativaValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jListEntidadEducativaValueChanged

    private void jListCategoriaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListCategoriaValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jListCategoriaValueChanged

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        int op = JOptionPane.showConfirmDialog(rootPane, "¿Esta seguro que desea cargar estos aspirantes?", "Confirmación", JOptionPane.QUESTION_MESSAGE);
        if(op==0)
        {
            limpiarInscripciones();
            cargarInscripcionesAspirantesNuevos();
            this.gestorRegistrarAspirante.cargarInscripciones(this.registroAspirantesNuevos);
            this.inscripciones.clear();
            this.registroAspirantesNuevos.clear();
            System.exit(0);
        }
        
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        int op = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro que desea salir?", "Salir", WIDTH, JOptionPane.QUESTION_MESSAGE);
        if(op==0)
            System.exit(0);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void cargarNuevoAspirante(Aspirante nuevoAspirante)
    {
        this.registroAspirantesNuevos.add(nuevoAspirante);       
    }
    
    private boolean buscarAspiranteInscripcionDni(int dniNuevoAspirante)
    {
        Iterator i = this.inscripciones.iterator();
        while(i.hasNext())
        {
            Inscripcion aux = (Inscripcion) i.next();
            if(aux.getAspirante().getDni()== dniNuevoAspirante)
                return true;
        }
        return false;
    }
    
    private boolean controlFormulario()
    {
        Pattern patron = Pattern.compile("[^A-Za-z ]");
        Matcher match = null;
        String error = "";
        try
        {
            match = patron.matcher(this.jTextFieldDialogNombre.getText());
            if(match.find() || this.jTextFieldDialogNombre.getText().length()==0)
                error = error + "Debe ingresar un nombre válido\n";
        }
        catch(Exception ex)
        {
            error = error + "Debe ingresar un nombre válido\n";
        }
        
        try
        {
            match = patron.matcher(this.jTextFieldDialogApellido.getText());
            if(match.find() || this.jTextFieldDialogApellido.getText().length()==0)
                error = error + "Debe ingresar un apellido válido\n";
        }
        catch(Exception ex)
        {
            error = error + "Debe ingresar un apellido válido\n";
        }
        
        try
        {
            patron = Pattern.compile("[^A-Za-z0-9 ]");
            match = patron.matcher(this.jTextFieldDialogDireccion.getText());
            if(match.find() || this.jTextFieldDialogDireccion.getText().length()==0)
                error = error + "Debe ingresar una dirección válida, solo se admiten Mayúsculas, Minúsculas y Números\n";
        }
        catch(Exception ex)
        {
            error = error + "Debe ingresar una dirección válida, solo se admiten Mayúsculas, Minúsculas y Números\n";
        }
        
        
        // control fecha para que la edad de entre 5 y 18!!!
        try
        {
            Date todayDate = new Date();
            int year = todayDate.getYear();
            Date minDate = new Date(year-18, 01, 01);
            Date maxDate = new Date(year-5,todayDate.getMonth(),todayDate.getDate());
            if(jDateChooserFechaDeNacimiento.getDate().before(minDate) || jDateChooserFechaDeNacimiento.getDate().after(maxDate))
                error = error + "Debe ingresar una fecha de nacimiento válida, el aspirante debe tener entre 5 y 18 años de edad\n";
        }
        catch(Exception ex)
        {
            error = error + "Debe ingresar una fecha de nacimiento válida\n";
        }
       
        
                
        if(!jRadioButtonFemenino.isSelected() && !jRadioButtonMasculino.isSelected())
            error = error + "Debe ingresar un sexo\n";
        
        try
        {
            patron = Pattern.compile("[^0-9]");
            match = patron.matcher(this.jTextFieldDialogDNI.getText());
            if(match.find() || this.jTextFieldDialogDNI.getText().length()<7 || this.jTextFieldDialogDNI.getText().length()>8)
                error = error + "Debe ingresar un DNI válido\n";
        }
        catch(Exception ex)
        {
            error = error + "Debe ingresar un DNI válido\n";
        }
        
        try
        {
            if(gestorRegistrarAspirante.buscarAspiranteDNI(Integer.valueOf(jTextFieldDialogDNI.getText())) || buscarAspiranteInscripcionDni(Integer.valueOf(jTextFieldDialogDNI.getText())))
            {
                error = error + "El DNI ingresado ya existe";
            }
        }
        catch(Exception ex)
        {
            error = error + "El DNI ingresado ya existe";
        }
        if(!error.equals(""))
        {
            JOptionPane.showMessageDialog(rootPane, error, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void limpiarFormularioAspirante()
    {
        this.jTextFieldDialogNombre.setText("");
        this.jTextFieldDialogApellido.setText("");
        this.jTextFieldDialogDireccion.setText("");
        this.jTextFieldDialogDNI.setText("");
        this.jRadioButtonFemenino.setSelected(false);
        this.jRadioButtonMasculino.setSelected(false);
        this.jDateChooserFechaDeNacimiento.setDate(null);
    }
    
    private void limpiarInscripciones()
    {
        List<Inscripcion>inscripcionesLimpias = new ArrayList();
        int add = 0;
        for(Inscripcion aux : this.inscripciones)
        {
            add = 0;
            for(Inscripcion auxLimpia : inscripcionesLimpias)
            {
                if(auxLimpia.getAspirante().equals(aux.getAspirante()) && auxLimpia.getCategoria().equals(aux.getCategoria()) && auxLimpia.getCompetencia().equals(aux.getCompetencia()))
                {
                    add=1;
                }
            }
            if(add==0)
            {
                inscripcionesLimpias.add(aux);
            }
        }
        this.inscripciones.clear();
        this.inscripciones.addAll(inscripcionesLimpias);
    }
    
    private void cargarInscripcionesAspirantesNuevos()
    {
        for(Inscripcion i: this.inscripciones)
        {
            for(Aspirante a: this.registroAspirantesNuevos)
            {
                if(i.getAspirante().equals(a))
                    a.getInscripciones().add(i);
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonDialogAceptar;
    private javax.swing.JButton jButtonDialogCancelar;
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonExit1;
    private javax.swing.JButton jButtonMax;
    private javax.swing.JButton jButtonMax1;
    private javax.swing.JButton jButtonMin;
    private javax.swing.JButton jButtonMin1;
    private javax.swing.JButton jButtonNuevoAspirante;
    private com.toedter.calendar.JDateChooser jDateChooserFechaDeNacimiento;
    private javax.swing.JDialog jDialogNuevoAspirante;
    private javax.swing.JLabel jLabelDialogApellido;
    private javax.swing.JLabel jLabelDialogDNI;
    private javax.swing.JLabel jLabelDialogDireccion;
    private javax.swing.JLabel jLabelDialogFechaDeNacimiento;
    private javax.swing.JLabel jLabelDialogNombre;
    private javax.swing.JLabel jLabelDialogSexo;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTituloNuevoAspirante;
    private javax.swing.JList jListAspirantes;
    private javax.swing.JList jListCategoria;
    private javax.swing.JList jListEntidadEducativa;
    private javax.swing.JPanel jPanelAspirantes;
    private javax.swing.JPanel jPanelBarraTarea;
    private javax.swing.JPanel jPanelBarraTarea1;
    private javax.swing.JPanel jPanelCategorias;
    private javax.swing.JPanel jPanelCompetencias;
    private javax.swing.JPanel jPanelEntidadEducativa;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JPanel jPanelFondo1;
    private javax.swing.JPanel jPanelIterior;
    private javax.swing.JPanel jPanelIterior1;
    private javax.swing.JRadioButton jRadioButtonFemenino;
    private javax.swing.JRadioButton jRadioButtonMasculino;
    private javax.swing.JScrollPane jScrollPaneAspirantes;
    private javax.swing.JScrollPane jScrollPaneCategoria;
    private javax.swing.JScrollPane jScrollPaneCompetencias;
    private javax.swing.JScrollPane jScrollPaneEntidadEducativa;
    private javax.swing.JTable jTableCompetencias;
    private javax.swing.JTextField jTextFieldDialogApellido;
    private javax.swing.JTextField jTextFieldDialogDNI;
    private javax.swing.JTextField jTextFieldDialogDireccion;
    private javax.swing.JTextField jTextFieldDialogNombre;
    // End of variables declaration//GEN-END:variables
}
