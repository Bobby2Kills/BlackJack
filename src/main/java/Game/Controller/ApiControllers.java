package Game.Controller;

import Game.Models.User;
import Game.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {
    @Autowired
    private UserRepo userRepo;
    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveUser(@RequestParam String playerName) {
        User user = new User();
        user.setPlayerName(playerName);
        userRepo.save(user);
        System.out.println("New user Saved: " + playerName);
        return "Saved User...";
    }

    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = userRepo.findById(id).orElse(null);
        if(updatedUser != null) {
            updatedUser.setPlayerName(user.getPlayerName());
            userRepo.save(updatedUser);
            System.out.println("User details updated");
            return "Updated...";
        }
        else {
            return "User not found";
        }
    }

    @DeleteMapping(value= "/delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser = userRepo.findById(id).get();
        userRepo.delete(deleteUser);
        System.out.println("User details deleted");
        return "User Deleted...";
    }


}
