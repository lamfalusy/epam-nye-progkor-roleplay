package hu.nye.progkor.roleplay.service;

import hu.nye.progkor.roleplay.model.RolePlay;
import java.util.List;

public interface RolePlayService {

  List<RolePlay> getAllRolePlays();

  RolePlay getRolePlay(Long id);

  RolePlay createRolePlay(RolePlay rolePlay);

  RolePlay updateRolePlay(Long id, RolePlay rolePlayChange);

  void deleteRolePlay(Long id);
}
