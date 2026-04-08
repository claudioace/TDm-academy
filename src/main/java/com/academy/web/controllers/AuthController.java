@Controller
public class AuthController {
    private final UserService service;
    
    public AuthController(UserService _service) {
        this.service = _service;
    }

    @GetMapping("/login")
    private String returnFormLogin(){
        return "login";
    }
    
    @GetMapping("/register")
    public String returnFormRegister(Model model){
        model.addAttribute("user", new UserDTO());
        return "register";
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute UserDTO user){
    this.service.register(user);

    return "redirect:/login";
}