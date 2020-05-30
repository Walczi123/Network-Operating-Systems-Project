package central.app.backend.centralapp.controllers;

import central.app.backend.centralapp.errors.ErrorResponse;
import central.app.backend.centralapp.exceptions.BookingNotExistException;
import central.app.backend.centralapp.forms.BookingForm;
import central.app.backend.centralapp.forms.PageForm;
import central.app.backend.centralapp.models.Booking;
import central.app.backend.centralapp.models.User;
import central.app.backend.centralapp.services.BookingService;
import central.app.backend.centralapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/")
public class BookingController {

    @Autowired
    public UserService userService;
    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("")
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking) {
        return ResponseEntity.ok().body(bookingService.create(booking));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("")
    public ResponseEntity<PageForm> getAllBookings(@RequestParam(name = "startDateFrom", required = false) String startDateFrom,
                                                   @RequestParam(name = "startDateTo", required = false) String startDateTo,
                                                   @RequestParam(name = "endDateFrom", required = false) String endDateFrom,
                                                   @RequestParam(name = "endDateTo", required = false) String endDateTo,
                                                   @RequestParam(name = "costUp", required = false) Double costUp,
                                                   @RequestParam(name = "costDown", required = false) Double costDown,
                                                   @RequestParam(name = "username", required = false) String username,
                                                   @RequestParam(name = "postCode", required = false) String postCode,
                                                   @RequestParam(name = "city", required = false) String city,
                                                   @RequestParam(name = "street", required = false) String street,
                                                   @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestHeader(name = "Authorization") String requestAuthorizationHeader) {
        String token = requestAuthorizationHeader.substring(7);
        User currentUser = userService.getUserByToken(token);
        return ResponseEntity.ok().body(bookingService.getAll(startDateFrom, startDateTo, endDateFrom, endDateTo, costUp, costDown, postCode, city, street, username, pageSize, pageNumber, currentUser));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<BookingForm> getBooking(@PathVariable(value = "id") int id,
                                                  @RequestHeader("Authorization") String requestAuthorizationHeader) {
        String token = requestAuthorizationHeader.substring(7);
        User currentUser = userService.getUserByToken(token);
        return ResponseEntity.ok().body(bookingService.get(id, currentUser));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable(value = "id") int id,
                                                @RequestHeader("Authorization") String requestAuthorizationHeader) {
        String token = requestAuthorizationHeader.substring(7);
        User currentUser = userService.getUserByToken(token);
        return ResponseEntity.ok().body(bookingService.delete(id, currentUser));
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/{id}")
    public ResponseEntity<BookingForm> updateBooking(@PathVariable(value = "id") int id,
                                                     @Valid @RequestBody Booking booking,
                                                     @RequestHeader("Authorization") String requestAuthorizationHeader) {
        String token = requestAuthorizationHeader.substring(7);
        User currentUser = userService.getUserByToken(token);
        return ResponseEntity.ok().body(bookingService.update(id, booking, currentUser));
    }

    @ExceptionHandler({BookingNotExistException.class})
    public ResponseEntity<ErrorResponse> notFound(BookingNotExistException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("Booking does not exists", HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorResponse> accessDenied(AccessDeniedException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("Access denied. You don't have enough permissions.", HttpStatus.FORBIDDEN.value(), ex.getMessage()),
                HttpStatus.FORBIDDEN);
    }

}
