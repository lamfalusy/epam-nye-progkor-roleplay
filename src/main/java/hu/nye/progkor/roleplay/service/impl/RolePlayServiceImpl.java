package hu.nye.progkor.roleplay.service.impl;

import hu.nye.progkor.roleplay.model.Complexity;
import hu.nye.progkor.roleplay.model.Genre;
import hu.nye.progkor.roleplay.model.RolePlay;
import hu.nye.progkor.roleplay.model.exception.NotFoundException;
import hu.nye.progkor.roleplay.service.RolePlayService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePlayServiceImpl implements RolePlayService {

  private final List<RolePlay> dataBase = new ArrayList<>();

  @Autowired
  public RolePlayServiceImpl() {
    dataBase.add(new RolePlay(1L, "Blade Runner", Genre.SCI_FI, Complexity.MEDIUM));
    dataBase.add(new RolePlay(2L, "D&D 5E", Genre.FANTASY, Complexity.LOW));
  }

  public RolePlayServiceImpl(final List<RolePlay> rolePlays) {
    dataBase.addAll(rolePlays);
  }

  @Override
  public List<RolePlay> getAllRolePlays() {
    return Collections.unmodifiableList(dataBase);
  }

  @Override
  public RolePlay getRolePlay(final Long id) {
    return dataBase.stream()
            .filter(rolePlay -> rolePlay.getId().equals(id))
            .findFirst()
            .orElseThrow(NotFoundException::new);
  }

  @Override
  public RolePlay createRolePlay(final RolePlay rolePlay) {
    rolePlay.setId(getNextId());
    dataBase.add(rolePlay);
    return rolePlay;
  }

  @Override
  public RolePlay updateRolePlay(final Long id, final RolePlay rolePlayChange) {
    final RolePlay rolePlay = getRolePlay(id);
    rolePlay.setName(rolePlayChange.getName());
    rolePlay.setGenre(rolePlayChange.getGenre());
    rolePlay.setComplexity(rolePlayChange.getComplexity());
    return rolePlay;
  }

  @Override
  public void deleteRolePlay(final Long id) {
    final RolePlay rolePlay = getRolePlay(id);
    dataBase.remove(rolePlay);
  }

  private long getNextId() {
    return getLastId() + 1L;
  }

  private long getLastId() {
    return dataBase.stream()
            .mapToLong(RolePlay::getId)
            .max()
            .orElse(0);
  }
}
