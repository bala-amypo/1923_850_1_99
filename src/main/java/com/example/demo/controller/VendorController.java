@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorRepository repo;

    public VendorController(VendorRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Vendor create(@RequestBody Vendor v) {
        return repo.save(v);
    }

    @GetMapping
    public List<Vendor> getAll() {
        return repo.findAll();
    }
}
