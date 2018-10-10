package technology.rocketjump.rebbl.bingo;

import java.util.Objects;

public class BingoItem {

	public final String name;
	public final String description; // Used for rulings
	public final BingoEventLikelihood likelihood;

	public BingoItem(String name, BingoEventLikelihood likelihood, String description) {
		this.name = name;
		this.description = description;
		this.likelihood = likelihood;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BingoItem bingoItem = (BingoItem) o;
		return Objects.equals(name, bingoItem.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	/**
	 * Used as a rough guide as to how likely a bingo event is to happen
	 */
	public enum BingoEventLikelihood {

		VERY_UNLIKELY,
		UNLIKELY,
		SOMEWHAT_LIKELY,
		VERY_LIKELY;

	}
}
