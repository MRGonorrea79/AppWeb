package ec.epn.edu.appweb.controller;

import ec.epn.edu.appweb.inventario.DAO.PrendaDAO;
import ec.epn.edu.appweb.inventario.DAO.TipoPrendaDAO;
import ec.epn.edu.appweb.inventario.modelo.Prenda;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
public class GestionarPrendasController {

    private final PrendaDAO prendaDAO;
    private final TipoPrendaDAO tipoPrendaDAO;

    public GestionarPrendasController(PrendaDAO prendaDAO,
                                      TipoPrendaDAO tipoPrendaDAO) {
        this.prendaDAO = prendaDAO;
        this.tipoPrendaDAO = tipoPrendaDAO;
    }
    @GetMapping("/")
    public String inicio() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String usuario, @RequestParam String password) {
        // Por ahora, cualquier usuario/password funciona
        // Más adelante puedes agregar validación real
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "dashboard";
    }

    @GetMapping("/logout")
    public String cerrarSesion() {
        return "redirect:/login";
    }

    @GetMapping("/prendas")
    public String listar(Model model) {
        model.addAttribute("prendas", prendaDAO.findAll());
        return "lista-prendas";
    }

    @GetMapping("/prendas/agregar")
    public String agregar(@RequestParam(required = false) String tipo, Model model) {
        model.addAttribute("prenda", new Prenda());
        model.addAttribute("tipos", tipoPrendaDAO.findAll());
        model.addAttribute("tipoOrigen", tipo); // Para saber a dónde volver
        return "agregar-prenda";
    }

    @PostMapping("/prendas")
    public String guardar(@ModelAttribute Prenda prenda, @RequestParam(required = false) String tipo) {
        prendaDAO.save(prenda);

        // Redirigir a confirmación con el tipo
        if (tipo != null && !tipo.isEmpty()) {
            return "redirect:/confirmar?tipo=" + tipo;
        }

        return "redirect:/confirmar";
    }

    @GetMapping("/prendas/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("prenda",
                prendaDAO.findById(id).orElse(null));
        model.addAttribute("tipos",
                tipoPrendaDAO.findAll());
        return "editar-prenda";
    }

    @PostMapping("/prendas/actualizar/{id}")
    public String actualizar(@PathVariable Long id,
                             @ModelAttribute Prenda prenda) {
        prenda.setId(id);
        prendaDAO.save(prenda);
        return "redirect:/confirmar";
    }

    @GetMapping("/prendas/eliminar/{id}")
    public String mostrarMensajeEliminacion(@PathVariable Long id, Model model) {
        Prenda prenda = prendaDAO.findById(id).orElse(null);
        model.addAttribute("prenda", prenda);

        return "eliminar-prenda";
    }

    @GetMapping("/prendas/eliminar/confirmar/{id}")
    public String eliminarPrenda(@PathVariable Long id) {

        Prenda prenda = prendaDAO.findById(id).orElse(null);
        if (prenda != null) {
            prendaDAO.eliminarPrenda(prenda);
        }

        return "redirect:/confirmar";
    }

    @GetMapping("/prendas/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("prenda",
                prendaDAO.findById(id).orElse(null));
        return "detalle-prenda";
    }

    @GetMapping("/confirmar")
    public String mostrarConfirmacion(@RequestParam(required = false) String tipo, Model model) {
        model.addAttribute("tipoOrigen", tipo);
        return "confirmar-accion";
    }


    @GetMapping("/hoddies")
    public String listarHoodies(Model model) {
        List<Prenda> hoodies = prendaDAO.findByTipoPrendaId(4L);
        System.out.println("Hoodies encontrados: " + hoodies.size());
        model.addAttribute("prendas", hoodies);
        model.addAttribute("tipoPrendaNombre", "Hoodies");
        model.addAttribute("tipoUrl", "hoddies");
        return "lista-prendas";
    }

    @GetMapping("/camisetas")
    public String listarCamisetas(Model model) {
        List<Prenda> camisetas = prendaDAO.findByTipoPrendaId(1L);
        System.out.println("Camisetas encontradas: " + camisetas.size());
        model.addAttribute("prendas", camisetas);
        model.addAttribute("tipoPrendaNombre", "Camisetas");
        model.addAttribute("tipoUrl", "camisetas");
        return "lista-prendas";
    }

    @GetMapping("/gorras")
    public String listarGorras(Model model) {
        List<Prenda> gorras = prendaDAO.findByTipoPrendaId(2L);
        System.out.println("Gorras encontradas: " + gorras.size());
        model.addAttribute("prendas", gorras);
        model.addAttribute("tipoPrendaNombre", "Gorras");
        model.addAttribute("tipoUrl", "gorras");
        return "lista-prendas";
    }

    @GetMapping("/pantalones")
    public String listarPantalones(Model model) {
        List<Prenda> pantalones = prendaDAO.findByTipoPrendaId(3L);
        System.out.println("Pantalones encontrados: " + pantalones.size());
        model.addAttribute("prendas", pantalones);
        model.addAttribute("tipoPrendaNombre", "Pantalones");
        model.addAttribute("tipoUrl", "pantalones");
        return "lista-prendas";
    }


}


