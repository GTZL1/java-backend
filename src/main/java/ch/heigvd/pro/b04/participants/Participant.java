package ch.heigvd.pro.b04.participants;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import ch.heigvd.pro.b04.votes.Vote;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participant {

  /**
   * Creates a new unique identifier for a Participant.
   *
   * @param repository The repository to which we will add a new Participant to
   * @return A new unique identifier
   */
  public static Long getNewIdentifier(ParticipantRepository repository) {
    Long identifier = repository.findAll().stream()
        .map(Participant::getIdParticipant)
        .map(ParticipantIdentifier::getIdParticipant)
        .max(Long::compareTo)
        .map(id -> id + 1)
        .orElse(1L);
    return identifier;
  }

  @Getter
  @EmbeddedId
  private ParticipantIdentifier idParticipant;

  @Column(unique = true)
  @Getter
  private String token;

  @OneToMany(mappedBy = "idVote.idxParticipant", cascade = CascadeType.ALL)
  private Set<Vote> voteSet;
}
