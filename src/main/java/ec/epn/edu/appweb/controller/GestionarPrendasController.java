package ec.epn.edu.appweb.controller;

import ec.epn.edu.appweb.inventario.DAO.PrendaDAO;
import ec.epn.edu.appweb.inventario.DAO.TipoPrendaDAO;
import ec.epn.edu.appweb.inventario.modelo.Prenda;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String index() {
        return "login";
    }
    @GetMapping("/login")
    public String login() {
        return "inicio";
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/prendas")
    public String listar(Model model) {
        model.addAttribute("prendas", prendaDAO.findAll());
        return "lista-prendas";
    }

    @GetMapping("/prendas/agregar")
    public String agregar(Model model) {
        model.addAttribute("prenda", new Prenda());
        model.addAttribute("tipos", tipoPrendaDAO.findAll());
        return "agregar-prenda";
    }

    @PostMapping("/prendas")
    public String guardar(@ModelAttribute Prenda prenda) {
        prendaDAO.save(prenda);
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
    public String confirmarEliminar(@PathVariable Long id, Model model) {
        model.addAttribute("prenda",
                prendaDAO.findById(id).orElse(null));
        return "eliminar-prenda";
    }

    @GetMapping("/prendas/eliminar/confirmar/{id}")
    public String eliminarPrenda(@PathVariable Long id) {
        prendaDAO.deleteById(id);
        return "redirect:/confirmar";
    }

    @GetMapping("/prendas/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        model.addAttribute("prenda",
                prendaDAO.findById(id).orElse(null));
        return "detalle-prenda";
    }

    @GetMapping("/confirmar")
    public String confirmar() {
        return "confirmar-accion";
    }
}

