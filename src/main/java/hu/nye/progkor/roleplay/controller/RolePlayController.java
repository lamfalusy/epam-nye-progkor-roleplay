package hu.nye.progkor.roleplay.controller;

import hu.nye.progkor.roleplay.model.RolePlay;
import hu.nye.progkor.roleplay.model.exception.NotFoundException;
import hu.nye.progkor.roleplay.service.RolePlayService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/role-play")
public class RolePlayController {

  private static final String ROLE_PLAY_LIST_TEMPLATE_NAME = "roleplay/list";
  private static final String ROLE_PLAY_EDIT_TEMPLATE_NAME = "roleplay/edit";
  private static final String ROLE_PLAY_ATTRIBUTE_NAME = "rolePlay";

  private final RolePlayService rolePlayService;

  public RolePlayController(final RolePlayService rolePlayService) {
    this.rolePlayService = rolePlayService;
  }

  @GetMapping
  public String getAllRolePlay(final Model model) {
    final List<RolePlay> rolePlays = rolePlayService.getAllRolePlays();
    model.addAttribute("rolePlays", rolePlays);
    return ROLE_PLAY_LIST_TEMPLATE_NAME;
  }

  @GetMapping("/{id}")
  public String getRolePlay(final Model model, final @PathVariable Long id) {
    final RolePlay rolePlay = rolePlayService.getRolePlay(id);
    model.addAttribute(ROLE_PLAY_ATTRIBUTE_NAME, rolePlay);
    return ROLE_PLAY_EDIT_TEMPLATE_NAME;
  }


  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String updateRolePlay(final Model model,
                               final @RequestParam(value = "id", required = false) Long id,
                               final RolePlay rolePlayChanges) {
    final RolePlay rolePlay = rolePlayService.updateRolePlay(id, rolePlayChanges);
    model.addAttribute(ROLE_PLAY_ATTRIBUTE_NAME, rolePlay);
    return ROLE_PLAY_EDIT_TEMPLATE_NAME;
  }

  @GetMapping("/create")
  public String createRolePlayForm(final Model model) {
    return "roleplay/create";
  }

  @PostMapping("/create")
  public String createRolePlay(final Model model, final RolePlay rolePlay) {
    final RolePlay savedRolePlay = rolePlayService.createRolePlay(rolePlay);
    model.addAttribute(ROLE_PLAY_ATTRIBUTE_NAME, savedRolePlay);
    return ROLE_PLAY_EDIT_TEMPLATE_NAME;
  }

  @GetMapping("/{id}/delete")
  public String deleteRolePlay(final Model model, final @PathVariable("id") Long id) {
    try {
      rolePlayService.deleteRolePlay(id);
    } catch (NotFoundException e) {
      // Ignored
    }
    final List<RolePlay> rolePlays = rolePlayService.getAllRolePlays();
    model.addAttribute("rolePlays", rolePlays);
    return ROLE_PLAY_LIST_TEMPLATE_NAME;
  }
}
