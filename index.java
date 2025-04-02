import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Usuario {
    private String usuario, nombre, apellido, telefono, email, contrasena;

    public Usuario(String usuario, String nombre, String apellido, String telefono, String email, String contrasena) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
    }

    public String getUsuario() { return usuario; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getContrasena() { return contrasena; }
}

public class LoginApp {
    private static List<Usuario> usuarios = new ArrayList<>();
    private static JFrame loginFrame;

    public static void main(String[] args) {
        mostrarLogin();
    }

    private static void mostrarLogin() {
        loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 300);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new GridLayout(4, 2));
        loginFrame.getContentPane().setBackground(new Color(255, 228, 225));

        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField();
        JLabel lblContrasena = new JLabel("Contraseña:");
        JPasswordField txtContrasena = new JPasswordField();
        JButton btnLogin = new JButton("Iniciar sesión");
        JButton btnRegistro = new JButton("Registrarse");
        JLabel lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.RED);

        loginFrame.add(lblUsuario);
        loginFrame.add(txtUsuario);
        loginFrame.add(lblContrasena);
        loginFrame.add(txtContrasena);
        loginFrame.add(lblMensaje);
        loginFrame.add(btnLogin);
        loginFrame.add(btnRegistro);

        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String contrasena = new String(txtContrasena.getPassword());
            boolean encontrado = false;
            for (Usuario u : usuarios) {
                if (u.getUsuario().equals(usuario) && u.getContrasena().equals(contrasena)) {
                    encontrado = true;
                    loginFrame.setVisible(false);
                    mostrarListaUsuarios();
                    break;
                }
            }
            if (!encontrado) lblMensaje.setText("Usuario o contraseña incorrectos");
        });

        btnRegistro.addActionListener(e -> {
            loginFrame.setVisible(false);
            mostrarRegistro();
        });

        loginFrame.setVisible(true);
    }

    private static void mostrarRegistro() {
        JFrame registroFrame = new JFrame("Registro");
        registroFrame.setSize(400, 400);
        registroFrame.setLayout(new GridLayout(8, 2));
        registroFrame.getContentPane().setBackground(new Color(230, 230, 250));

        JTextField txtUsuario = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JTextField txtTelefono = new JTextField();
        JTextField txtEmail = new JTextField();
        JPasswordField txtContrasena = new JPasswordField();
        JPasswordField txtConfirmarContrasena = new JPasswordField();
        JButton btnRegistrar = new JButton("Registrar");
        JLabel lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.RED);

        registroFrame.add(new JLabel("Usuario:"));
        registroFrame.add(txtUsuario);
        registroFrame.add(new JLabel("Nombre:"));
        registroFrame.add(txtNombre);
        registroFrame.add(new JLabel("Apellido:"));
        registroFrame.add(txtApellido);
        registroFrame.add(new JLabel("Teléfono:"));
        registroFrame.add(txtTelefono);
        registroFrame.add(new JLabel("Email:"));
        registroFrame.add(txtEmail);
        registroFrame.add(new JLabel("Contraseña:"));
        registroFrame.add(txtContrasena);
        registroFrame.add(new JLabel("Confirmar contraseña:"));
        registroFrame.add(txtConfirmarContrasena);
        registroFrame.add(lblMensaje);
        registroFrame.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            if (txtUsuario.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() ||
                    txtTelefono.getText().isEmpty() || txtEmail.getText().isEmpty() ||
                    txtContrasena.getPassword().length == 0 || txtConfirmarContrasena.getPassword().length == 0) {
                lblMensaje.setText("Todos los campos son obligatorios");
                return;
            }

            String contrasena = new String(txtContrasena.getPassword());
            String confirmarContrasena = new String(txtConfirmarContrasena.getPassword());
            if (!contrasena.equals(confirmarContrasena)) {
                lblMensaje.setText("Las contraseñas no coinciden");
                return;
            }

            usuarios.add(new Usuario(txtUsuario.getText(), txtNombre.getText(), txtApellido.getText(),
                    txtTelefono.getText(), txtEmail.getText(), contrasena));
            registroFrame.setVisible(false);
            mostrarLogin();
        });

        registroFrame.setVisible(true);
    }

    private static void mostrarListaUsuarios() {
        JFrame usuariosFrame = new JFrame("Usuarios registrados");
        usuariosFrame.setSize(400, 300);
        usuariosFrame.setLayout(new BorderLayout());
        usuariosFrame.getContentPane().setBackground(new Color(255, 240, 245));

        JTextArea areaUsuarios = new JTextArea();
        areaUsuarios.setEditable(false);
        for (Usuario u : usuarios) {
            areaUsuarios.append("Nombre: " + u.getNombre() + " " + u.getApellido() + ", Tel: " + u.getTelefono() + ", Email: " + u.getEmail() + "\n");
        }

        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.addActionListener(e -> {
            usuariosFrame.setVisible(false);
            mostrarLogin();
        });

        usuariosFrame.add(new JScrollPane(areaUsuarios), BorderLayout.CENTER);
        usuariosFrame.add(btnCerrarSesion, BorderLayout.SOUTH);
        usuariosFrame.setVisible(true);
    }
}