package hu.nye.progkor.roleplay.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import hu.nye.progkor.roleplay.model.Complexity;
import hu.nye.progkor.roleplay.model.Genre;
import hu.nye.progkor.roleplay.model.RolePlay;
import hu.nye.progkor.roleplay.model.exception.NotFoundException;
import hu.nye.progkor.roleplay.service.RolePlayService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RolePlayServiceImplTest {

  private static final RolePlay BLADE_RUNNER_ROLE_PLAY = new RolePlay(1L, "Blade Runner", Genre.SCI_FI, Complexity.MEDIUM);
  private static final RolePlay DND5E_ROLE_PLAY = new RolePlay(2L, "D&D 5E", Genre.FANTASY, Complexity.LOW);
  private static final List<RolePlay> ROLE_PLAYS = List.of(
          BLADE_RUNNER_ROLE_PLAY,
          DND5E_ROLE_PLAY
  );
  public static final long UNKNOWN_ROLE_PLAY_ID = -1L;
  public static final String CTHULHU_ROLE_PLAY_NAME = "Cthulhu";

  private RolePlayService underTest;

  @BeforeEach
  void setUp() {
    underTest = new RolePlayServiceImpl(ROLE_PLAYS);
  }

  @Test
  void getAllRolePlaysShouldReturnAllRolePlays() {
    // when
    final List<RolePlay> actual = underTest.getAllRolePlays();
    // then
    assertThat(actual).isEqualTo(ROLE_PLAYS);
  }

  @Test
  void getRolePlayShouldReturnRolePlayWhenGivenIdOfExistingRolePlay() {
    // when
    final RolePlay actual = underTest.getRolePlay(DND5E_ROLE_PLAY.getId());
    // then
    assertThat(actual).isEqualTo(DND5E_ROLE_PLAY);
  }

  @Test
  void getRolePlayShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingRolePlay() {
    // when then
    assertThrows(NotFoundException.class, () -> underTest.getRolePlay(UNKNOWN_ROLE_PLAY_ID));
  }

  @Test
  void createRolePlayShouldReturnRoleyPlayWhenDelegateIt() {
    // given
    final RolePlay cthulhuRolePlay = new RolePlay(null, CTHULHU_ROLE_PLAY_NAME, Genre.HORROR, Complexity.MEDIUM);
    final RolePlay expectedCthulhuRolePlay = new RolePlay(3L, CTHULHU_ROLE_PLAY_NAME, Genre.HORROR, Complexity.MEDIUM);
    // when
    final RolePlay actual = underTest.createRolePlay(cthulhuRolePlay);
    // then
    assertThat(actual).isEqualTo(expectedCthulhuRolePlay);
  }

  @Test
  void updateRolePlayShouldReturnUpdatedRolePlayWhenGivenIdOfExistingRolePlay() {
    // given
    final RolePlay cthulhuRolePlay = new RolePlay(null, CTHULHU_ROLE_PLAY_NAME, Genre.HORROR, Complexity.MEDIUM);
    final RolePlay expectedRolePlay = new RolePlay(DND5E_ROLE_PLAY.getId(), CTHULHU_ROLE_PLAY_NAME, Genre.HORROR, Complexity.MEDIUM);
    // when
    final RolePlay actual = underTest.updateRolePlay(DND5E_ROLE_PLAY.getId(), cthulhuRolePlay);
    // then
    assertThat(actual).isEqualTo(expectedRolePlay);
  }

  @Test
  void updateRolePlayShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingRolePlay() {
    // when then
    assertThrows(NotFoundException.class, () -> underTest.getRolePlay(UNKNOWN_ROLE_PLAY_ID));
  }

  @Test
  void deleteRolePlayShouldDeleteRolePlayWhenGivenIdOfRolePlay() {
    // given
    final List<RolePlay> expectedRolePlays = List.of(DND5E_ROLE_PLAY);
    // when
    underTest.deleteRolePlay(BLADE_RUNNER_ROLE_PLAY.getId());
    final List<RolePlay> actual = underTest.getAllRolePlays();
    // then
    assertThat(actual).isEqualTo(expectedRolePlays);
  }
}
