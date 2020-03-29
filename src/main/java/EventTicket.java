public class EventTicket extends Ticket  {

    String ticketType; //seisukoht või istekoht
    String place; //loož, rida, koht
    Event event; //piletile vastav üritus


    public EventTicket(String name, double price, String ticketType, Event event) {
        super(name, price);
        this.ticketType = ticketType;
        this.event=event;
    }

    @Override
    public Owner getOwner() {
        return event.getOwner();
    }

    @Override
    public void give() {   //TODO!: PDF genereerimine ja meilile saatmine siia , kui yes-mail==true;
        //Võiks kuvada ka ekraanil ostetud piletit.
    }
}
