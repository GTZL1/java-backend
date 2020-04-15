package ch.heigvd.pro.b04.session;

import ch.heigvd.pro.b04.sessions.exceptions.SessionNotAvailableException;
import ch.heigvd.pro.b04.sessions.*;
import ch.heigvd.pro.b04.sessions.exceptions.SessionCodeNotHexadecimalException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionControllerTest {
    @InjectMocks
    SessionController session;

    @Mock
    SessionRepository repository;

    @Test
    public void testIfSessionIsClosed() {
        Session currentSession = new Session(123);
        currentSession.setState(SessionState.CLOSED);

        SessionCode code = SessionCode.builder().hexadecimal("0x123F").build();
        when(repository.findByCode(any())).thenReturn(Optional.of(currentSession));

        assertThrows(SessionNotAvailableException.class, () -> session.byCode(code));
    }

    @Test
    public void testIfSessionIsClosedToNewOnes() {
        Session currentSession = new Session(123);
        currentSession.setState(SessionState.CLOSED_TO_NEW_ONES);

        SessionCode code = SessionCode.builder().hexadecimal("0x123F").build();
        when(repository.findByCode(any())).thenReturn(Optional.of(currentSession));

        assertThrows(SessionNotAvailableException.class, () -> session.byCode(code));
    }

    @Test
    public void testIfSessionCodeIsHexadecimal() {
        Session currentSession = new Session(123);

        SessionCode code = SessionCode.builder().hexadecimal("abcd").build();
        when(repository.findByCode(any())).thenReturn(Optional.of(currentSession));

        assertThrows(SessionCodeNotHexadecimalException.class, () -> session.byCode(code));
    }

    @Test
    public void testIfSessionCodeIsAlmostHexadecimal() {
        Session currentSession = new Session(123);

        SessionCode code = SessionCode.builder().hexadecimal("11FE").build();
        when(repository.findByCode(any())).thenReturn(Optional.of(currentSession));

        assertThrows(SessionCodeNotHexadecimalException.class, () -> session.byCode(code));
    }
}
