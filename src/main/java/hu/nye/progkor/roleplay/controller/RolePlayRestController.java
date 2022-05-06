package hu.nye.progkor.roleplay.controller;

import hu.nye.progkor.roleplay.model.RolePlay;
import hu.nye.progkor.roleplay.model.exception.NotFoundException;
import hu.nye.progkor.roleplay.service.RolePlayService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role-play")
public class RolePlayRestController {

  private final RolePlayService rolePlayService;

  public RolePlayRestController(final RolePlayService rolePlayService) {
    this.rolePlayService = rolePlayService;
  }

  @GetMapping
  public List<RolePlay> getAllRolePlays() {
    return rolePlayService.getAllRolePlays();
  }

  @GetMapping("/{id}")
  RolePlay getRolePlay(final @PathVariable Long id) {
    return rolePlayService.getRolePlay(id);
  }

  @PostMapping
  RolePlay createRolePlay(final @RequestBody RolePlay rolePlay) {
    return rolePlayService.createRolePlay(rolePlay);
  }

  @PutMapping("/{id}")
  RolePlay updateRolePlay(final @PathVariable Long id, final @RequestBody RolePlay rolePlayChange) {
    return rolePlayService.updateRolePlay(id, rolePlayChange);
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Void> deleteRolePlay(final @PathVariable Long id) {
    try {
      rolePlayService.deleteRolePlay(id);
    } catch (NotFoundException e) {
      // Ignored
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
