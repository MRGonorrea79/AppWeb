package ec.epn.edu.appweb.controller;

import ec.epn.edu.appweb.inventario.DAO.PrendaDAO;
import ec.epn.edu.appweb.inventario.modelo.Prenda;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GestionarPrendasController {

    private final PrendaDAO prendaDAO;

    public GestionarPrendasController(PrendaDAO prendaDAO) {
        this.prendaDAO = prendaDAO;
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
        model.addAttribute("prenda", prendaDAO.findById(id).orElse(null));
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
