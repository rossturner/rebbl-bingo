package technology.rocketjump.rebbl.bingo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static technology.rocketjump.rebbl.bingo.BingoItem.BingoEventLikelihood.*;

public class App {

	private static List<BingoItem> allEvents = Arrays.asList(
			new BingoItem("Tripwire!", VERY_LIKELY, "A player carrying the ball fails a GFI into the endzone"),
			new BingoItem("Death on the REBBL Logo", VERY_LIKELY, "A player outright dies on one of the squares covered by the REBBL logo (shown in an official cast)"),
			new BingoItem("Pile On punished", SOMEWHAT_LIKELY, "A PO player knocks an opponent down, doesn't get an AV break, piles on, still doesn't get an AV break, which sets up the opponent to get a good foul in and remove the piling on player. "),
			new BingoItem("Metal declares a play 'bold'", VERY_LIKELY, "While streaming a match, FullMetal declares that a coach's decision is 'Bold' aka he hates it"),
			new BingoItem("Pro reroll into dub skulls", SOMEWHAT_LIKELY, "A player with Pro makes a block or blitz, rerolls with pro, and gets a double skulls result"),
			new BingoItem("It went to kicks!", UNLIKELY, "A playoff match ended in a draw and was decided by kicks"),
			new BingoItem("Big Guy first action", SOMEWHAT_LIKELY, "The first action involving rolling dice on a player's turn was using a Big Guy player (Str5+ and Loner)"),
			new BingoItem("'Playoff nerves' play", VERY_LIKELY, "A playoff coach takes an absolutely bonkers action that nobody watching can understand and has almost no positive benefit"),
			new BingoItem("Induced a bribe and never fouled", VERY_UNLIKELY, "A coach induced a bribe and then never fouled all game"),
			new BingoItem("Star player removed early", VERY_LIKELY, "The team's only legend player OR a star player is removed from the pitch by the end of turn 3 and does not rejoin the match"),
			new BingoItem("Death by leap", UNLIKELY, "A player leaps, fails the leap, and dies"),
			new BingoItem("Turn 16 'Respect' foul", VERY_LIKELY, "Either coach fouls on the last turn of the game (16 normally, 24 in overtime)"),
			new BingoItem("First action dub-skulls", VERY_LIKELY, "The very first action on a coach's turn involving dice is a double (or triple) skulls. Single die skull into another skull also counts."),
			new BingoItem("Piling on with the ball carrier", VERY_UNLIKELY, "A player carrying the ball piles on, causing a turnover"),
			new BingoItem("Fouling with the ball carrier", SOMEWHAT_LIKELY, "A player carrying the ball is used to foul, regardless of the result"),
			new BingoItem("Apo-ing a skink", UNLIKELY, "A coach uses an apothecary to reroll a skink casualty"),
			new BingoItem("Surfing a player instead of scoring a touchdown", VERY_UNLIKELY, "A coach can reasonably score in a turn, but commits players to surfing an opponent first"),
			new BingoItem("Total potato", SOMEWHAT_LIKELY, "A coach runs the player with the ball past the opposing team into a position they can be easily blitzed"),
			new BingoItem("Pitch clear", SOMEWHAT_LIKELY, "At some point during a turn, one team does not have any players on the pitch"),
			new BingoItem("Unexpected caster rage at a bad play", SOMEWHAT_LIKELY, "A normally reserved caster or co-caster has an absolute outburst of rage at what they perceive to be a poor decision"),
			new BingoItem("No-assist foul into casualty", UNLIKELY, "A player makes a foul with no assists, which results in a casualty. Regenerated casualties still count."),
			new BingoItem("Wizard Fizzle", VERY_LIKELY, "A wizard was induced but either rolls 1 on the lightning bolt or a fireball fails to knock down a single player"),
			new BingoItem("Bribe induced but no fouls called out", UNLIKELY, "A team that induces a bribe makes takes multiple foul actions, but never rolls a double in the match for the ref to call it out"),
			new BingoItem("Misclick unpunished", SOMEWHAT_LIKELY, "A coach performs a misclick but does not suffer from the effects of it, e.g. accidental foul which is not sent off, (accidental?) pass to the wrong spot is caught, ball carrier moved out of position but not sacked, red die block into knockdown or pushes"),
			new BingoItem("Misclick punished", SOMEWHAT_LIKELY, "A coach performs a misclick and feels the full force of Nuffle's wrath for it, e.g. accidental foul which is sent off, blitzing the wrong player to not sack the ball, red die block into skull"),
			new BingoItem("One die pow on a blodger", VERY_LIKELY, "A player without tackle makes a 1d block (or worse), and finds a pow (without a reroll) on a player with blodge"),
			new BingoItem("Big guy 3d skulls", UNLIKELY, "A big guy makes a block or blitz to roll 3 dice, and they all come up skulls (still counts if rerolled)"),
			new BingoItem("The All Apple Pies Blitz", SOMEWHAT_LIKELY, "A level 3 or above player does not have block or wrestle but does have mighty blow, is used for a blitz which results in a both down or skull (still counts if rerolled)"),
			new BingoItem("Cast(er) crashers", VERY_LIKELY, "A technical problem causes an official playoff cast to lose audio or video at some point, most likely by BB2 crashing"),
			new BingoItem("Deathfloor strikes again", VERY_LIKELY, "A failed GFI results in a death or perm injury. Still counts if apo is used."),
			new BingoItem("Killer apothecary", VERY_LIKELY, "An apothecary rerolls a MNG or perm injury into a dead result"),
			new BingoItem("Co-caster spoilers", VERY_LIKELY, "A co-caster is slightly ahead of the caster's stream and spoils at least two events for the audience"),
			new BingoItem("Greed pile-on rewarded", VERY_LIKELY, "A PO player doesn't get an armour break on a knockdown, piles on, and gets both an AV break and a removal"),
			new BingoItem("Big guy was useless", UNLIKELY, "A big guy was particularly useless, failing above average negatrait checks and otherwise not having much impact on the game, AKA the Unf award"),
			new BingoItem("OTTD Shootout", UNLIKELY, "Both coaches attempt a one turn touchdown in the same match"),
			new BingoItem("Orc GFI fails", VERY_LIKELY, "A player on an orc team (but not a goblin) fails a GFI"),
			new BingoItem("500k or more in inducements", SOMEWHAT_LIKELY, "An unfortunate matchup means one coach gets 500k or more in inducements"),
			new BingoItem("Pillow fight", SOMEWHAT_LIKELY, "Two bashy teams with kill skills play each other and results in nothing worse than a badly hurt (loners do not count)"),
			new BingoItem("Big Guy was amazing", VERY_UNLIKELY, "A big guy (Str%+ and loner) never failing a negatrait check or otherwise making a game winning play on low odds (E.g. Morg scoring a touchdown, an AG1 or AG2 dodge to sack the ball)"),
			new BingoItem("Shadowing works", SOMEWHAT_LIKELY, "A player with the shadowing skill actually gets to activate it successfully"),
			new BingoItem("DT is a fake skill", UNLIKELY, "A match involves 2 or more dodges away from Diving Tackle, which never triggers in the whole match")
	);


	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("This must be called with a single string argument - the coaches name");
			return;
		}

		String coachName = args[0];
		Random random = new Random(coachName.hashCode());



	}
}
