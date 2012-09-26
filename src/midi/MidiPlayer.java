package midi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Transmitter;

public class MidiPlayer {
	private static boolean DEBUG = false;
	private static Sequencer sm_sequencer = null;
	private static List sm_openedMidiDeviceList;
	private static boolean sm_bFinished = false;

	boolean bUseSynthesizer = false;
	boolean bUseMidiPort = false;
	boolean bUseDevice = true;			// output midi to piano!
	boolean bUseConsoleDump = false;
	
	String strDeviceName = null;
	String strSequencerName = null;
	
	
	public void openMidi(String strFileName) {
		File midiFile = new File(strFileName);
		
		/* 
		 * create sequence object from input file  
		 */
		Sequence sequence = null;
		try {
			if (DEBUG) out("before MIDI file reading.");
			sequence = MidiSystem.getSequence(midiFile);
		} catch (InvalidMidiDataException e) {
			printExceptionAndExit(e);
		} catch (IOException e) {
			printExceptionAndExit(e);
		}
		
		/*
		 * Get a default sequencer to play the sequence
		 */
		try {
			sm_sequencer = MidiSystem.getSequencer(false);
		} catch (MidiUnavailableException e) {
			printExceptionAndExit(e);
		} 
		if (sm_sequencer == null) {
			out("MidiPlayer.main(): can't get a Sequencer");
			System.exit(1);
		}
		if (DEBUG) out("Sequencer: " + sm_sequencer);
		
		/*
		 * Exit with a meta event listener
		 * 
		 * insert code later.
		 * 
		 */
		
		/*
		 * Open the sequencer
		 */
		try {
			sm_sequencer.open();
		} catch (MidiUnavailableException e) {
			printExceptionAndExit(e);
		} 
		if (DEBUG) out("Sequencer opened");
		
		/*
		 * Tell the sequencer which sequence to play
		 */
		try
		{
			sm_sequencer.setSequence(sequence);
		} catch (InvalidMidiDataException e)
		{
			printExceptionAndExit(e);
		}
		if (DEBUG) out("Sequence set");
		
		
		/*
		 * Set destination sequence should be played on
		 * We're sending it to disklavier
		 */
		sm_openedMidiDeviceList = new ArrayList();
		if (bUseDevice) {
			MidiDevice.Info[] aInfos = MidiSystem.getMidiDeviceInfo();
			MidiDevice.Info info = aInfos[1];	// when plugged in, disklavier is device #1
			if (info == null) {
				out("cannot find disklavier device");
			}
			try {
				MidiDevice midiDevice = MidiSystem.getMidiDevice(info);
				midiDevice.open();
				sm_openedMidiDeviceList.add(midiDevice);
				Receiver midiReceiver = midiDevice.getReceiver();
				Transmitter midiTransmitter = sm_sequencer.getTransmitter();
				midiTransmitter.setReceiver(midiReceiver);
			} catch (MidiUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void playMidi() {
		sm_sequencer.start();
	}
	
	public void pauseMidi() {
		sm_sequencer.stop();	// problem: pedal doesn't get held
	}
	
	public void rewindMidi() {
		sm_sequencer.setMicrosecondPosition(0);
	}
	
	private static void printExceptionAndExit(Exception e)
	{
		e.printStackTrace();
		System.exit(1);
	}
	
	private static void out(String strMessage)
	{
		System.out.println(strMessage);
	}

}
