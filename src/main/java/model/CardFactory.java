package model;

public class CardFactory {
    public static Card createCard(CardType type) {
        CardAction action = null;

        switch (type) {
            case SHUFFLE:
                action = new ActionShuffle();
                break;
            case SWAP_TOP_AND_BOTTOM:
                action = new ActionSwapTopBottom();
                break;
            case NOPE:
                action = new ActionNope();
                break;
            case EXPLODING_KITTEN:
                action = new ActionPassive("Exploding Kitten Incoming!");
                break;
            case DEFUSE:
                action = new ActionPassive("Defuse Card: Automatically used when you explode.");
                break;

            default:
                throw new IllegalArgumentException("Card type not supported: " + type);
        }

        return new Card(type, action);
    }
}
