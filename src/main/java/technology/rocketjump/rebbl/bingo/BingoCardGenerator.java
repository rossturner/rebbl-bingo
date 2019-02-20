package technology.rocketjump.rebbl.bingo;

import java.util.*;

import static technology.rocketjump.rebbl.bingo.BingoItem.BingoEventLikelihood.*;
import static technology.rocketjump.rebbl.bingo.BingoItem.BingoEventLikelihood.UNLIKELY;

public class BingoCardGenerator {

    public static final String CSV_DELIMITER = "|";

    private static List<BingoItem> allItems = Arrays.asList(
            new BingoItem("Tripwire!", VERY_LIKELY, "A player carrying the ball fails a GFI into the endzone"),
            new BingoItem("Pile On punished", SOMEWHAT_LIKELY, "A PO player knocks an opponent down, doesn't get an AV break, piles on, still doesn't get an AV break, which sets up the opponent to get a good foul in and remove the piling on player. "),
            new BingoItem("Star player removed early", VERY_LIKELY, "The team's only legend player OR a star player is removed from the pitch by the end of turn 3 and does not rejoin the match"),
            new BingoItem("Fouling with the ball carrier", UNLIKELY, "A player carrying the ball is used to foul, regardless of the result"),
            new BingoItem("Pro reroll into dub skulls", SOMEWHAT_LIKELY, "A player with Pro makes a block or blitz, rerolls with pro, and gets a double skulls result"),
            new BingoItem("Induced a bribe and never fouled", VERY_UNLIKELY, "A coach induced a bribe and then never fouled all game"),
            new BingoItem("It went to kicks!", UNLIKELY, "A playoff match ended in a draw and was decided by kicks"),
            new BingoItem("Big Guy first action", SOMEWHAT_LIKELY, "The first action involving rolling dice (that can cause a turnover) on a player's turn was using a Big Guy player (Str5+ and Loner). Actions with no risk of turnover (standing up, moving) do not count."),
            new BingoItem("Death by leap", UNLIKELY, "A player leaps, fails the leap, and dies"),
            new BingoItem("Turn 16 'Respect' foul", VERY_LIKELY, "Either coach fouls on the last turn of the game (16 normally, 24 in overtime)"),
            new BingoItem("First action dub-skulls", VERY_LIKELY, "The very first action on a coach's turn involving dice is a double (or triple) skulls. Single die skull into another skull also counts."),
            new BingoItem("Piling on with the ball carrier", VERY_UNLIKELY, "A player carrying the ball piles on, causing a turnover"),
            new BingoItem("Surfing a player instead of scoring a touchdown", VERY_UNLIKELY, "A coach can reasonably score in a turn, but commits players to surfing an opponent first"),
            new BingoItem("Apo-ing a skink", UNLIKELY, "A coach uses an apothecary to reroll a skink casualty"),
            new BingoItem("Total potato", SOMEWHAT_LIKELY, "A coach runs the player with the ball past the opposing team into a position they can be easily blitzed, requiring no more than a single dice roll e.g. dodge"),
            new BingoItem("Pitch clear", SOMEWHAT_LIKELY, "At some point during a turn, one team does not have any players on the pitch"),
            new BingoItem("No-assist foul into casualty", UNLIKELY, "A player makes a foul with no assists, which results in a casualty. Regenerated casualties still count."),
            new BingoItem("Wizard Fizzle", VERY_LIKELY, "A wizard was induced but either rolls 1 on the lightning bolt or a fireball fails to knock down a single player"),
            new BingoItem("Bribe induced but no fouls called out", UNLIKELY, "A team that induces a bribe makes takes multiple foul actions, but never rolls a double in the match for the ref to call it out"),
            new BingoItem("One die pow on a blodger", VERY_LIKELY, "A player without tackle makes a 1d block (or worse), and finds a pow (without a reroll) on a player with blodge"),
            new BingoItem("The All Apple Pies Blitz", SOMEWHAT_LIKELY, "A level 3 or above player does not have block or wrestle but does have mighty blow, is used for a blitz which results in a both down or skull (still counts if rerolled)"),
            new BingoItem("Deathfloor is not safer", SOMEWHAT_LIKELY, "A failed dodge from a player with Mighty Blow or Claw results in a death or perm injury. Still counts if apo is used."),
            new BingoItem("Deathfloor strikes again", SOMEWHAT_LIKELY, "A failed GFI results in a death or perm injury. Still counts if apo is used."),
            new BingoItem("Killer apothecary", VERY_LIKELY, "An apothecary rerolls a MNG or perm injury into a dead result"),
            new BingoItem("Greed pile-on rewarded", VERY_LIKELY, "A PO player doesn't get an armour break on a knockdown, piles on, and gets both an AV break and a removal"),
            new BingoItem("Big guy was useless", UNLIKELY, "A big guy was particularly useless, failing above average negatrait checks and otherwise not having much impact on the game, AKA the Unf award"),
            new BingoItem("OTTD Shootout", UNLIKELY, "Both coaches attempt a one turn touchdown in the same match"),
            new BingoItem("500k or more in inducements", SOMEWHAT_LIKELY, "An unfortunate matchup means one coach gets 500k or more in inducements"),
            new BingoItem("Pillow fight", SOMEWHAT_LIKELY, "Two bashy teams with kill skills play each other and results in nothing worse than a badly hurt (loners do not count)"),
            new BingoItem("Big Guy was amazing", VERY_UNLIKELY, "A big guy (Str%+ and loner) never failing a negatrait check or otherwise making a game winning play on low odds (E.g. Morg scoring a touchdown, an AG1 or AG2 dodge to sack the ball)"),
            new BingoItem("Shadowing works", SOMEWHAT_LIKELY, "A player with the shadowing skill actually gets to activate it successfully"),
            new BingoItem("Forgot to stab", UNLIKELY, "A player with stab skill is on the pitch (induced or rostered) and does not use stab all match"),
            new BingoItem("DT is a fake skill", UNLIKELY, "A match involves 2 or more dodges away from Diving Tackle, which never triggers in the whole match"),
            new BingoItem("Would've killed a tree!", UNLIKELY, "A player without Mighty Blow rolls 11 or 12 for the armour roll, into a casualty, into death"),

            new BingoItem("Shouldn't have scattered it", UNLIKELY, "A bouncing ball is caught by an opposing player (the team not currently taking a turn)"),
            new BingoItem("Always scatter it!", SOMEWHAT_LIKELY, "A bouncing ball is caught by a player on the team currently taking a turn"),
            new BingoItem("Edward Scatterhands", SOMEWHAT_LIKELY, "A bouncing ball is caught on a 6+"),
            new BingoItem("Mine!", UNLIKELY, "A player blitzes the ball carrier, knocks it free, and catches the scatter"),
            new BingoItem("Yours...", VERY_UNLIKELY, "The ball carrier drops the ball as part of a turnover, and an opposing player catches it"),

            new BingoItem("Run ragged", UNLIKELY, "A team fails 6 or more GFIs in a single match"),
            new BingoItem("Long Bong Silver Award", UNLIKELY, "A long bomb pass succeeds"),
            new BingoItem("Nice Agi 5, idiot", SOMEWHAT_LIKELY, "An agi 5 player snakes an action"),
            new BingoItem("Nice Str 5, idiot", UNLIKELY, "A strength 5 or more player makes a 3 die block and causes a turnover"),
            new BingoItem("Interception Inception", VERY_UNLIKELY, "Both teams successfully intercept a pass at some point in the match"),
            new BingoItem("Eldril Sideliner", UNLIKELY, "Eldril Sidewinder is induced and removed from the pitch in the first half, not returning until turn 16 or at all"),

            new BingoItem("Never rely on the Krox", SOMEWHAT_LIKELY, "A big guy fails their nega-trait roll on a Blitz action"),
            new BingoItem("Manatee Ass!", SOMEWHAT_LIKELY, "A player levels up due to a completed pass"),
            new BingoItem("Crowd full of elves", UNLIKELY, "A player catches a ball thrown from the crowd before it bounces"),

            new BingoItem("Playoffs don't count", UNLIKELY, "A chaos dwarf blocker with Claw dies"),
            new BingoItem("Blood in the water", VERY_LIKELY, "Two casulaties have been inflicted by the end of the first turn - rocks do not count"),
            new BingoItem("Fun and interactive", UNLIKELY, "Sweltering heat removes 4 or more players from one team"),
            new BingoItem("Fun and interactive v2", UNLIKELY, "A pitch invasion stuns 6 or more players from one team"),
            new BingoItem("We came here for blood!", VERY_LIKELY, "No casualties have been inflicted by the end of the first half"),
            new BingoItem("Ultimate Lucker Noob award", VERY_UNLIKELY, "A player without wrestle, tackle or strip ball successfully sacks the ball on an uphill (red die) blitz or block"),
            new BingoItem("Blitz the scoring threat!", UNLIKELY, "A coach fails to blitz the one possible scoring threat when he was able to do so without making dodges, the scoring threat then scores"),
            new BingoItem("You Did The Hard Bit!", SOMEWHAT_LIKELY, "A player escapes Tentacles but fails the dodge"),
            new BingoItem("Top of the Food Chain", VERY_UNLIKELY, "A coach pushes a player into scoring range and scores with him, having not conceded a touchdown on the previous turn"),
            new BingoItem("You're Not That Fast", VERY_LIKELY, "A player with +MA fails a GFI"),
            new BingoItem("Nice Level, Idiot", SOMEWHAT_LIKELY, "A player due to level dies or takes a perm injury in the same match, star players do not count"),

            new BingoItem("Nobody expects the Khemri Pass", UNLIKELY, "An agi2 player throws and completes a pass which results in a touchdown on the same turn"),
            new BingoItem("Not a team player", VERY_UNLIKELY, "A player with loner scores a touchdown"),

            new BingoItem("Tactical Nuke", VERY_UNLIKELY, "A wizard fireball KOs or injures 3 or more players"),
            new BingoItem("Struck down by Zeus", VERY_UNLIKELY, "A level 5 or above player not carrying the ball is killed or permed by a lightning bolt")


    );

    private Map<BingoItem.BingoEventLikelihood, List<BingoItem>> likelihoodGroups = new HashMap<>();
    private Map<BingoItem.BingoEventLikelihood, Integer> numToPickPerGroup = new HashMap<>();

    public BingoCardGenerator() {
        for (BingoItem.BingoEventLikelihood likelihood : BingoItem.BingoEventLikelihood.values()) {
            likelihoodGroups.put(likelihood, new ArrayList<>());
        }

        for (BingoItem item : allItems) {
            likelihoodGroups.get(item.likelihood).add(item);
        }

        System.out.println("Count of bingo items by likelihood:");
        for (Map.Entry<BingoItem.BingoEventLikelihood, List<BingoItem>> mapEntry : likelihoodGroups.entrySet()) {
            System.out.print(mapEntry.getKey().name() + ": " + mapEntry.getValue().size());

            float proportion = ((float) mapEntry.getValue().size()) / ((float) allItems.size());
            int numToPick = Math.round(proportion * (7 * 7 - 1));
            numToPickPerGroup.put(mapEntry.getKey(), numToPick);
            System.out.println("\tTotal to pick: " + numToPick);
        }
    }

    public String generateBingoCard(String coachName) {
        List<BingoItem> selectedItems = selectItemsForCard(coachName);

        StringBuilder output = new StringBuilder();
        output.append(coachName).append("\n");

        for (int rowCursor = 0; rowCursor < 5; rowCursor++) {
            for (int columnCursor = 0; columnCursor < 5; columnCursor++) {
                BingoItem bingoItem = selectedItems.get(columnCursor + (rowCursor * 5));
                output.append(bingoItem.name).append(CSV_DELIMITER);
            }
            output.append("\n");
        }

        output.append("\n\n").append("Rules / descriptions:").append("\n");
        for (BingoItem selectedItem : selectedItems) {
            output.append(selectedItem.name)
                    .append(CSV_DELIMITER)
                    .append(CSV_DELIMITER)
                    .append(CSV_DELIMITER)
                    .append(selectedItem.description).append("\n");
        }

        return output.toString();
    }

    private List<BingoItem> selectItemsForCard(String coachName) {
        Random random = new Random(coachName.hashCode());

        List<BingoItem> selectedItems = new ArrayList<>();
        for (Map.Entry<BingoItem.BingoEventLikelihood, Integer> numToPickEntry : numToPickPerGroup.entrySet()) {
            int numToPickFromGroup = numToPickEntry.getValue();
            for (int cursor = 0; cursor < numToPickFromGroup; cursor++) {
                pickItem(selectedItems, random, likelihoodGroups.get(numToPickEntry.getKey()));
            }
        }

        Collections.shuffle(selectedItems, random);

        // Finally, add in center tile as a very_likely item
        pickItem(selectedItems, random, likelihoodGroups.get(VERY_LIKELY));

        BingoItem middleItem = selectedItems.remove(selectedItems.size() - 1);
        selectedItems.add(12, middleItem);

        return selectedItems;
    }

    private void pickItem(List<BingoItem> selectedItems, Random random, List<BingoItem> itemsToSelectFrom) {
        BingoItem selected = null;
        do {
            selected = itemsToSelectFrom.get(random.nextInt(itemsToSelectFrom.size()));
        } while (selectedItems.contains(selected));
        selectedItems.add(selected);
    }


}
