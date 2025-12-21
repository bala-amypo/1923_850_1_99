@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetRepository assetRepo;
    private final VendorRepository vendorRepo;
    private final DepreciationRuleRepository ruleRepo;

    public AssetController(AssetRepository assetRepo,
                           VendorRepository vendorRepo,
                           DepreciationRuleRepository ruleRepo) {
        this.assetRepo = assetRepo;
        this.vendorRepo = vendorRepo;
        this.ruleRepo = ruleRepo;
    }

    @PostMapping("/{vendorId}/{ruleId}")
    public Asset create(@PathVariable Long vendorId,
                        @PathVariable Long ruleId,
                        @RequestBody Asset asset) {

        asset.setVendor(vendorRepo.findById(vendorId).orElseThrow());
        asset.setDepreciationRule(ruleRepo.findById(ruleId).orElseThrow());
        asset.setStatus("ACTIVE");
        return assetRepo.save(asset);
    }

    @GetMapping
    public List<Asset> getAll() {
        return assetRepo.findAll();
    }

    @GetMapping("/{id}")
    public Asset getById(@PathVariable Long id) {
        return assetRepo.findById(id).orElseThrow();
    }
}
