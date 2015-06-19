package net.nostromo.libc.c;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * <i>native declaration : linux/if_packet.h:11</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class sockaddr_ll extends Structure {
	public short sll_family;
	/** C type : __be16 */
	public short sll_protocol;
	public int sll_ifindex;
	public short sll_hatype;
	public byte sll_pkttype;
	public byte sll_halen;
	/** C type : unsigned char[8] */
	public byte[] sll_addr = new byte[8];
	public sockaddr_ll() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("sll_family", "sll_protocol", "sll_ifindex", "sll_hatype", "sll_pkttype", "sll_halen", "sll_addr");
	}
	/**
	 * @param sll_protocol C type : __be16<br>
	 * @param sll_addr C type : unsigned char[8]
	 */
	public sockaddr_ll(short sll_family, short sll_protocol, int sll_ifindex, short sll_hatype, byte sll_pkttype,
			byte sll_halen, byte sll_addr[]) {
		super();
		this.sll_family = sll_family;
		this.sll_protocol = sll_protocol;
		this.sll_ifindex = sll_ifindex;
		this.sll_hatype = sll_hatype;
		this.sll_pkttype = sll_pkttype;
		this.sll_halen = sll_halen;
		if ((sll_addr.length != this.sll_addr.length))
			throw new IllegalArgumentException("Wrong array size !");
		this.sll_addr = sll_addr;
	}
	public sockaddr_ll(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends sockaddr_ll implements Structure.ByReference {

	};
	public static class ByValue extends sockaddr_ll implements Structure.ByValue {

	};
}
