package Practicas.Compilador;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class InterfazCompilador extends JFrame {

    // Componentes principales
    private JTextArea editorCodigo;
    private JTextArea analizadorLexico;
    private JTextArea analizadorSintactico;
    private JTextArea consolaSalida;
    private JLabel nombreArchivo;
    private JTextArea numerosLinea;
    private JLabel statusBar;
    private JProgressBar progressBar;
    
    // Gestión de archivos
    private File archivoActual;
    
    // Analizadores
    private Lexico analizador = new Lexico();
    private Sintactico analizadorSint = new Sintactico();
    
    // Timer para análisis
    private Timer timerSintaxis;
    
    // Paleta de colores moderna y minimalista
    private static final Color DARK_BG = new Color(20, 23, 28);           // Fondo oscuro principal
    private static final Color SIDEBAR_BG = new Color(30, 33, 39);        // Sidebar más claro  
    private static final Color EDITOR_BG = new Color(252, 252, 252);      // Editor claro
    private static final Color CARD_BG = new Color(255, 255, 255);        // Tarjetas blancas
    private static final Color ACCENT_BLUE = new Color(59, 130, 246);     // Azul moderno
    private static final Color ACCENT_GREEN = new Color(34, 197, 94);     // Verde éxito
    private static final Color ACCENT_RED = new Color(239, 68, 68);       // Rojo error
    private static final Color TEXT_PRIMARY = new Color(17, 24, 39);      // Texto principal
    private static final Color TEXT_SECONDARY = new Color(107, 114, 128); // Texto secundario
    private static final Color BORDER_LIGHT = new Color(229, 231, 235);   // Bordes claros
    private static final Color HOVER_BG = new Color(243, 244, 246);       // Hover suave
    
    // Tipografías optimizadas
    private static final Font FONT_EDITOR = new Font("JetBrains Mono", Font.PLAIN, 14);
    private static final Font FONT_UI = new Font("Inter", Font.PLAIN, 13);
    private static final Font FONT_UI_BOLD = new Font("Inter", Font.BOLD, 13);
    private static final Font FONT_TITLE = new Font("Inter", Font.BOLD, 16);

    public InterfazCompilador() {
        configurarLookAndFeel();
        configurarVentana();
        crearInterfaz();
        configurarTimer();
        configurarAtajos();
        
        setVisible(true);
    }
    
    private void configurarLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            
            // Personalizar colores del UIManager
            UIManager.put("Panel.background", CARD_BG);
            UIManager.put("OptionPane.background", CARD_BG);
            UIManager.put("Button.background", ACCENT_BLUE);
            UIManager.put("Button.foreground", Color.WHITE);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void configurarVentana() {
        setTitle("Hybrid Compiler Studio");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(1000, 600));
        setIconImage(crearIconoVentana());
        
        // Fondo principal
        getContentPane().setBackground(EDITOR_BG);
    }
    
    private Image crearIconoVentana() {
        // Crear un icono simple para la ventana
        BufferedImage icono = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = icono.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(ACCENT_BLUE);
        g2.fillRoundRect(4, 4, 24, 24, 8, 8);
        
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString("C", 12, 20);
        
        g2.dispose();
        return icono;
    }
    
    private void crearInterfaz() {
        setLayout(new BorderLayout(0, 0));
        
        // Header elegante
        add(crearHeader(), BorderLayout.NORTH);
        
        // Contenido principal
        add(crearContenidoPrincipal(), BorderLayout.CENTER);
        
        // Footer con status
        add(crearFooter(), BorderLayout.SOUTH);
    }
    
    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(CARD_BG);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_LIGHT));
        header.setPreferredSize(new Dimension(0, 65));
        
        // Título y archivo
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        leftPanel.setBackground(CARD_BG);
        
        JLabel titulo = new JLabel("Hybrid Compiler");
        titulo.setFont(FONT_TITLE);
        titulo.setForeground(TEXT_PRIMARY);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        nombreArchivo = new JLabel("sin-titulo.hyb");
        nombreArchivo.setFont(FONT_UI);
        nombreArchivo.setForeground(TEXT_SECONDARY);
        nombreArchivo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        leftPanel.add(titulo);
        leftPanel.add(crearSeparadorVertical());
        leftPanel.add(nombreArchivo);
        
        // Botones de acción principales
        JPanel rightPanel = crearBotonesHeader();
        
        header.add(leftPanel, BorderLayout.WEST);
        header.add(rightPanel, BorderLayout.EAST);
        
        return header;
    }
    
    private JPanel crearBotonesHeader() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
        panel.setBackground(CARD_BG);
        
        // Botones principales con iconos y estilo moderno
        panel.add(crearBotonHeader("Nuevo", "⊞", e -> nuevoArchivo()));
        panel.add(crearBotonHeader("Abrir", "⊡", e -> abrirArchivo()));
        panel.add(crearBotonHeader("Guardar", "⊟", e -> guardarArchivo()));
        
        panel.add(crearSeparadorVertical());
        
        // Botones de compilación con colores distintivos
        JButton btnLexico = crearBotonCompilacion("Léxico", ACCENT_BLUE, e -> realizarAnalisisLexico());
        JButton btnSintactico = crearBotonCompilacion("Sintáctico", ACCENT_GREEN, e -> realizarAnalisisSintactico());
        JButton btnCompilar = crearBotonCompilacion("Compilar", ACCENT_RED, e -> compilarTodo());
        
        panel.add(btnLexico);
        panel.add(btnSintactico);
        panel.add(btnCompilar);
        
        return panel;
    }
    
    private JButton crearBotonHeader(String texto, String icono, java.awt.event.ActionListener accion) {
        JButton boton = new JButton(icono + " " + texto);
        boton.setFont(FONT_UI);
        boton.setForeground(TEXT_PRIMARY);
        boton.setBackground(HOVER_BG);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addActionListener(accion);
        
        // Efectos hover
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(BORDER_LIGHT);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(HOVER_BG);
            }
        });
        
        return boton;
    }
    
    private JButton crearBotonCompilacion(String texto, Color color, java.awt.event.ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.setFont(FONT_UI_BOLD);
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addActionListener(accion);
        
        // Efectos hover para botones de compilación
        Color colorOriginal = color;
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorOriginal.darker());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorOriginal);
            }
        });
        
        return boton;
    }
    
    private Component crearSeparadorVertical() {
        JPanel separador = new JPanel();
        separador.setBackground(BORDER_LIGHT);
        separador.setPreferredSize(new Dimension(1, 20));
        return separador;
    }
    
    private JPanel crearContenidoPrincipal() {
        JPanel panel = new JPanel(new BorderLayout(12, 0));
        panel.setBackground(EDITOR_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        
        // Editor (lado izquierdo)
        JPanel panelEditor = crearPanelEditor();
        
        // Resultados (lado derecho) 
        JPanel panelResultados = crearPanelResultados();
        
        // Usar un layout más sofisticado
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelEditor, panelResultados);
        splitPane.setDividerLocation(700);
        splitPane.setDividerSize(8);
        splitPane.setOneTouchExpandable(false);
        splitPane.setBorder(null);
        splitPane.setBackground(EDITOR_BG);
        
        panel.add(splitPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelEditor() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(CARD_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_LIGHT, 1),
            BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        
        // Header del editor
        JPanel headerEditor = new JPanel(new BorderLayout());
        headerEditor.setBackground(HOVER_BG);
        headerEditor.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        
        JLabel labelEditor = new JLabel("Editor de Código");
        labelEditor.setFont(FONT_UI_BOLD);
        labelEditor.setForeground(TEXT_PRIMARY);
        
        // Indicador de lenguaje
        JLabel labelLenguaje = new JLabel("Hybrid Language");
        labelLenguaje.setFont(FONT_UI);
        labelLenguaje.setForeground(TEXT_SECONDARY);
        
        headerEditor.add(labelEditor, BorderLayout.WEST);
        headerEditor.add(labelLenguaje, BorderLayout.EAST);
        
        // Área de código con números de línea mejorados
        editorCodigo = new JTextArea();
        editorCodigo.setFont(FONT_EDITOR);
        editorCodigo.setTabSize(4);
        editorCodigo.setBackground(CARD_BG);
        editorCodigo.setForeground(TEXT_PRIMARY);
        editorCodigo.setCaretColor(ACCENT_BLUE);
        editorCodigo.setSelectionColor(new Color(59, 130, 246, 50));
        editorCodigo.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        editorCodigo.setLineWrap(false);
        
        // Números de línea estilizados
        numerosLinea = new JTextArea("1");
        numerosLinea.setFont(FONT_EDITOR);
        numerosLinea.setEditable(false);
        numerosLinea.setBackground(HOVER_BG);
        numerosLinea.setForeground(TEXT_SECONDARY);
        numerosLinea.setBorder(BorderFactory.createEmptyBorder(16, 12, 16, 8));
        
        JScrollPane scrollEditor = new JScrollPane(editorCodigo);
        scrollEditor.setRowHeaderView(numerosLinea);
        scrollEditor.setBorder(null);
        scrollEditor.getViewport().setBackground(CARD_BG);
        
        // Listener para números de línea
        editorCodigo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    actualizarNumerosLinea();
                    reiniciarTimerSintaxis();
                });
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    actualizarNumerosLinea();
                    reiniciarTimerSintaxis();
                });
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        
        panel.add(headerEditor, BorderLayout.NORTH);
        panel.add(scrollEditor, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 8, 0));
        panel.setBackground(EDITOR_BG);
        panel.setBorder(null);
        
        // Panel Léxico
        JPanel panelLexico = new JPanel(new BorderLayout());
        panelLexico.setBackground(CARD_BG);
        panelLexico.setBorder(BorderFactory.createLineBorder(BORDER_LIGHT, 1));
        
        JPanel headerLexico = new JPanel(new BorderLayout());
        headerLexico.setBackground(HOVER_BG);
        headerLexico.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        JLabel labelLexico = new JLabel("📊 Análisis Léxico");
        labelLexico.setFont(FONT_UI_BOLD);
        labelLexico.setForeground(TEXT_PRIMARY);
        headerLexico.add(labelLexico, BorderLayout.WEST);
        
        analizadorLexico = crearAreaResultado();
        JScrollPane scrollLexico = new JScrollPane(analizadorLexico);
        scrollLexico.setBorder(null);
        
        panelLexico.add(headerLexico, BorderLayout.NORTH);
        panelLexico.add(scrollLexico, BorderLayout.CENTER);
        
        // Panel Sintáctico
        JPanel panelSintactico = new JPanel(new BorderLayout());
        panelSintactico.setBackground(CARD_BG);
        panelSintactico.setBorder(BorderFactory.createLineBorder(BORDER_LIGHT, 1));
        
        JPanel headerSintactico = new JPanel(new BorderLayout());
        headerSintactico.setBackground(HOVER_BG);
        headerSintactico.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        JLabel labelSintactico = new JLabel("🔍 Análisis Sintáctico");
        labelSintactico.setFont(FONT_UI_BOLD);
        labelSintactico.setForeground(TEXT_PRIMARY);
        headerSintactico.add(labelSintactico, BorderLayout.WEST);
        
        analizadorSintactico = crearAreaResultado();
        JScrollPane scrollSintactico = new JScrollPane(analizadorSintactico);
        scrollSintactico.setBorder(null);
        
        panelSintactico.add(headerSintactico, BorderLayout.NORTH);
        panelSintactico.add(scrollSintactico, BorderLayout.CENTER);
        
        panel.add(panelLexico);
        panel.add(panelSintactico);
        
        return panel;
    }
    
    private JTextArea crearAreaResultado() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(FONT_EDITOR);
        area.setBackground(CARD_BG);
        area.setForeground(TEXT_PRIMARY);
        area.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        return area;
    }
    
    private JPanel crearFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(CARD_BG);
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_LIGHT));
        footer.setPreferredSize(new Dimension(0, 35));
        
        // Status bar
        statusBar = new JLabel(" Listo");
        statusBar.setFont(FONT_UI);
        statusBar.setForeground(TEXT_SECONDARY);
        statusBar.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        
        // Progress bar oculta inicialmente
        progressBar = new JProgressBar();
        progressBar.setVisible(false);
        progressBar.setPreferredSize(new Dimension(120, 8));
        progressBar.setBackground(HOVER_BG);
        progressBar.setForeground(ACCENT_BLUE);
        
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 8));
        rightPanel.setBackground(CARD_BG);
        rightPanel.add(progressBar);
        
        footer.add(statusBar, BorderLayout.WEST);
        footer.add(rightPanel, BorderLayout.EAST);
        
        return footer;
    }
    
    private void configurarTimer() {
        timerSintaxis = new Timer(800, e -> {
            // Análisis en tiempo real si se desea
        });
        timerSintaxis.setRepeats(false);
    }
    
    private void reiniciarTimerSintaxis() {
        timerSintaxis.restart();
    }
    
    // Métodos de archivo optimizados
    private void nuevoArchivo() {
        archivoActual = null;
        nombreArchivo.setText("sin-titulo.hyb");
        editorCodigo.setText("");
        actualizarStatus("Nuevo archivo");
    }
    
    private void abrirArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos Híbridos (*.hyb)", "hyb"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivos de Texto (*.txt)", "txt"));
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            archivoActual = fileChooser.getSelectedFile();
            
            mostrarProgreso(true);
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    try (BufferedReader reader = new BufferedReader(new FileReader(archivoActual))) {
                        editorCodigo.read(reader, null);
                    }
                    return null;
                }
                
                @Override
                protected void done() {
                    try {
                        get(); // Verificar excepciones
                        nombreArchivo.setText(archivoActual.getName());
                        actualizarStatus("Abierto: " + archivoActual.getName());
                        actualizarNumerosLinea();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(InterfazCompilador.this, 
                            "Error al abrir archivo: " + e.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        mostrarProgreso(false);
                    }
                }
            };
            worker.execute();
        }
    }
    
    private void guardarArchivo() {
        if (archivoActual == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos Híbridos (*.hyb)", "hyb"));
            
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                archivoActual = fileChooser.getSelectedFile();
                if (!archivoActual.getName().toLowerCase().endsWith(".hyb")) {
                    archivoActual = new File(archivoActual.getAbsolutePath() + ".hyb");
                }
            } else {
                return;
            }
        }
        
        mostrarProgreso(true);
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoActual))) {
                    editorCodigo.write(writer);
                }
                return null;
            }
            
            @Override
            protected void done() {
                try {
                    get();
                    nombreArchivo.setText(archivoActual.getName());
                    actualizarStatus("Guardado: " + archivoActual.getName());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(InterfazCompilador.this, 
                        "Error al guardar archivo: " + e.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    mostrarProgreso(false);
                }
            }
        };
        worker.execute();
    }
    
    // Métodos de compilación mejorados
    private void realizarAnalisisLexico() {
        if (editorCodigo.getText().trim().isEmpty()) {
            mostrarAdvertencia("No hay código para analizar");
            return;
        }
        
        mostrarProgreso(true);
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            private int numTokens;
            
            @Override
            protected String doInBackground() throws Exception {
                List<Lexico.Token> tokens = analizador.analizar(editorCodigo.getText());
                numTokens = tokens.size();
                return "📊 ANÁLISIS LÉXICO\n" + "═".repeat(60) + "\n\n" + 
                       analizador.formatearTokens(tokens);
            }
            
            @Override
            protected void done() {
                try {
                    String resultado = get();
                    analizadorLexico.setText(resultado);
                    analizadorLexico.setCaretPosition(0);
                    actualizarStatus("Análisis léxico: " + numTokens + " tokens");
                } catch (Exception e) {
                	 
                } finally {
                    mostrarProgreso(false);
                }
            }
        };
        worker.execute();
    }
    
    private void realizarAnalisisSintactico() {
        if (editorCodigo.getText().trim().isEmpty()) {
            mostrarAdvertencia("No hay código para analizar");
            return;
        }
        
        mostrarProgreso(true);
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            private int numErrores;
            
            @Override
            protected String doInBackground() throws Exception {
                List<Lexico.Token> tokens = analizador.analizar(editorCodigo.getText());
                List<String> errores = analizadorSint.analizar(tokens);
                numErrores = errores.size();
                
                StringBuilder resultado = new StringBuilder();
                resultado.append("🔍 ANÁLISIS SINTÁCTICO\n");
                resultado.append("═".repeat(60)).append("\n\n");
                
                if (errores.isEmpty()) {
                    resultado.append("✅ Sin errores sintácticos\n");
                    resultado.append("🎉 El programa es válido según la gramática LL(1)\n");
                } else {
                    resultado.append("❌ Errores encontrados: ").append(errores.size()).append("\n\n");
                    for (int i = 0; i < errores.size(); i++) {
                        resultado.append((i + 1)).append(". ").append(errores.get(i)).append("\n");
                    }
                }
                
                return resultado.toString();
            }
            protected void done() {
                try {
                    String resultado = get();
                    analizadorSintactico.setText(resultado);
                    analizadorSintactico.setCaretPosition(0);
                    
                    if (numErrores == 0) {
                        actualizarStatus("✅ Análisis sintáctico completado sin errores");
                    } else {
                        actualizarStatus("❌ Análisis sintáctico: " + numErrores + " errores encontrados");
                    }
                    
                } catch (Exception e) {
                    // Manejar errores en el hilo principal
                    String mensajeError = "Error inesperado durante el análisis sintáctico: " + e.getMessage();
                    analizadorSintactico.setText("❌ " + mensajeError);
                    actualizarStatus("❌ Error en análisis sintáctico");
                    
                    // Mostrar diálogo de error para casos críticos
                    JOptionPane.showMessageDialog(InterfazCompilador.this, 
                        mensajeError, 
                        "Error de Análisis", 
                        JOptionPane.ERROR_MESSAGE);
                } finally {
                    mostrarProgreso(false);
                }
            }
            
        };
        worker.execute();
    }
    
    private void compilarTodo() {
        realizarAnalisisLexico();
        Timer timer = new Timer(1000, e -> {
            realizarAnalisisSintactico();
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    // Métodos auxiliares mejorados
    private void actualizarNumerosLinea() {
        String texto = editorCodigo.getText();
        String[] lineas = texto.split("\n", -1);
        
        StringBuilder numeros = new StringBuilder();
        for (int i = 1; i <= lineas.length; i++) {
            numeros.append(String.format("%4d", i)).append("\n");
        }
        
        numerosLinea.setText(numeros.toString());
    }
    
    private void actualizarStatus(String mensaje) {
        statusBar.setText(" " + mensaje);
    }
    
    private void mostrarProgreso(boolean mostrar) {
        progressBar.setVisible(mostrar);
        if (mostrar) {
            progressBar.setIndeterminate(true);
        } else {
            progressBar.setIndeterminate(false);
        }
    }
    
    private void mostrarAdvertencia(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }
    
    private void configurarAtajos() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();
        
        // Atajos optimizados
        inputMap.put(KeyStroke.getKeyStroke("control N"), "nuevo");
        actionMap.put("nuevo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { nuevoArchivo(); }
        });
        
        inputMap.put(KeyStroke.getKeyStroke("control O"), "abrir");
        actionMap.put("abrir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { abrirArchivo(); }
        });
        
        inputMap.put(KeyStroke.getKeyStroke("control S"), "guardar");
        actionMap.put("guardar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { guardarArchivo(); }
        });
        
        inputMap.put(KeyStroke.getKeyStroke("F5"), "lexico");
        actionMap.put("lexico", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { realizarAnalisisLexico(); }
        });
        
        inputMap.put(KeyStroke.getKeyStroke("F6"), "sintactico");
        actionMap.put("sintactico", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { realizarAnalisisSintactico(); }
        });
        
        inputMap.put(KeyStroke.getKeyStroke("F7"), "compilar");
        actionMap.put("compilar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { compilarTodo(); }
        });
    }
    
    public static void main(String[] args) {
        // Configurar propiedades del sistema para mejor rendering
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        
        SwingUtilities.invokeLater(() -> {
            new InterfazCompilador();
        });
    }
}