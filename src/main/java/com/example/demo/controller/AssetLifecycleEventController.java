@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {

    private final AssetLifecycleEventRepository eventRepo;
    private final AssetRepository assetRepo;

    public AssetLifecycleEventController(AssetLifecycleEventRepository eventRepo,
                                         AssetRepository assetRepo) {
        this.eventRepo = eventRepo;
        this.assetRepo = assetRepo;
    }

    @PostMapping("/{assetId}")
    public AssetLifecycleEvent log(@PathVariable Long assetId,
                                   @RequestBody AssetLifecycleEvent event) {
        event.setAsset(assetRepo.findById(assetId).orElseThrow());
        return eventRepo.save(event);
    }

    @GetMapping
    public List<AssetLifecycleEvent> getAll() {
        return eventRepo.findAll();
    }
}
