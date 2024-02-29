package ImperialCapitalist;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import objects.ResourceManager;

public class SettingsScreen {

	private JFrame SettingsFrame;

	ResourceManager resources = new ResourceManager();

	@SuppressWarnings("unchecked")
	public SettingsScreen(int i, MusicPlayer Musicobj) {
		// if i == 0, from main menu
		// if i == 1, from game screen

		// Frame for main menu
		SettingsFrame = new JFrame();
		SettingsFrame.setBounds(100, 100, 816, 600);
		SettingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SettingsFrame.getContentPane().setLayout(null);
		SettingsFrame.setVisible(true);
		SettingsFrame.setLocationRelativeTo(null);

		JLabel BackgroundLBL = new JLabel();
		BackgroundLBL.setBounds(0, 0, 800, 600);
		BackgroundLBL.setOpaque(true);
		BackgroundLBL.setIcon(resources.getImage(8));
		SettingsFrame.getContentPane().add(BackgroundLBL);

		JLabel TitleLBL = new JLabel("Imperial Capitalist");
		TitleLBL.setBounds(50, 25, 700, 150);
		TitleLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 75));
		TitleLBL.setForeground(Color.white);
		TitleLBL.setHorizontalAlignment(SwingConstants.CENTER);
		BackgroundLBL.add(TitleLBL);

		String[] SongChoices = {"Cantina Band", "Capitalist Music", "Imperial March", "Duel Of The Fates", "Binary Sunset"};
		@SuppressWarnings("rawtypes")
		JComboBox<?> AudioBTN = new JComboBox(SongChoices);
		AudioBTN.setSelectedIndex(Musicobj.getSongIndex());
		AudioBTN.setBounds(400, 250, 200, 40);
		AudioBTN.setFocusable(false);
		AudioBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		AudioBTN.setForeground(Color.black);
		AudioBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(AudioBTN);

		JLabel AudioLBL = new JLabel("Song Choice:");
		AudioLBL.setBounds(200, 250, 200, 40);
		AudioLBL.setFocusable(false);
		AudioLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		AudioLBL.setHorizontalAlignment(SwingConstants.CENTER);
		AudioLBL.setVerticalAlignment(SwingConstants.CENTER);
		AudioLBL.setForeground(Color.white);
		BackgroundLBL.add(AudioLBL);

		JSlider VolumeSlider = new JSlider();
		VolumeSlider.setValue(Musicobj.getVolume());
		VolumeSlider.setForeground(Color.blue);
		VolumeSlider.setOpaque(false);
		VolumeSlider.setBounds(400, 300, 200, 40);
		VolumeSlider.setFocusable(false);
		VolumeSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(VolumeSlider);

		JLabel VolumeLBL = new JLabel("Change Volume:");
		VolumeLBL.setBounds(200, 300, 200, 40);
		VolumeLBL.setFocusable(false);
		VolumeLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		VolumeLBL.setHorizontalAlignment(SwingConstants.CENTER);
		VolumeLBL.setVerticalAlignment(SwingConstants.CENTER);
		VolumeLBL.setForeground(Color.white);
		BackgroundLBL.add(VolumeLBL);

		JButton StopMusicBTN = new JButton();
		StopMusicBTN.setBounds(375, 335, 244, 130);
		if (Musicobj.getPlaying()) {
			StopMusicBTN.setIcon(resources.getScaledImage(22, 2, 2));
		}
		else {
			StopMusicBTN.setIcon(resources.getScaledImage(23, 2, 2));
		}
		StopMusicBTN.setHorizontalAlignment(SwingConstants.CENTER);
		StopMusicBTN.setFocusable(false);
		StopMusicBTN.setContentAreaFilled(false);
		StopMusicBTN.setBorderPainted(false);
		StopMusicBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		StopMusicBTN.setForeground(Color.white);
		StopMusicBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(StopMusicBTN);

		JLabel StopMusicLBL = new JLabel("Play Music: ");
		StopMusicLBL.setBounds(200, 385, 200, 40);
		StopMusicLBL.setFocusable(false);
		StopMusicLBL.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		StopMusicLBL.setHorizontalAlignment(SwingConstants.RIGHT);
		StopMusicLBL.setVerticalAlignment(SwingConstants.CENTER);
		StopMusicLBL.setForeground(Color.white);
		BackgroundLBL.add(StopMusicLBL);

		JButton BackBTN = new JButton("Back");
		BackBTN.setBounds(300, 450, 200, 40);
		BackBTN.setHorizontalAlignment(SwingConstants.CENTER);
		BackBTN.setFocusable(false);
		BackBTN.setContentAreaFilled(false);
		BackBTN.setBorderPainted(false);
		BackBTN.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		BackBTN.setForeground(Color.white);
		BackBTN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BackgroundLBL.add(BackBTN);

		BackBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SettingsFrame.dispose();
				if (i == 0 || i == 2) {
					new MainMenuScreen(Musicobj);
				}
			}
		});

		AudioBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedSong = (String) AudioBTN.getSelectedItem();
				if (selectedSong == "Capitalist Music") {
					Musicobj.stopClip();
					try {
						Musicobj.startClip(1);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (selectedSong == "Imperial March") {
					try {
						Musicobj.stopClip();
					} catch (Exception e2) {}
					try {
						Musicobj.startClip(2);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (selectedSong == "Cantina Band") {
					Musicobj.stopClip();
					try {
						Musicobj.startClip(0);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (selectedSong == "Duel Of The Fates") {
					Musicobj.stopClip();
					try {
						Musicobj.startClip(3);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (selectedSong == "Binary Sunset") {
					Musicobj.stopClip();
					try {
						Musicobj.startClip(4);
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				VolumeSlider.setValue(100);
				Musicobj.setPlaying(true);
				StopMusicBTN.setIcon(resources.getScaledImage(22, 2, 2));
			}
		});


		VolumeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider)event.getSource();
				if (!source.getValueIsAdjusting()) {
					int value = (int)source.getValue();
					Musicobj.clipVolume(value);
				}
			}
		});

		StopMusicBTN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Musicobj.getPlaying()) {
					Musicobj.stopClip();
				}
				else {
					try {
						Musicobj.startClip(Musicobj.getSongIndex());
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				Musicobj.setPlaying(!Musicobj.getPlaying());
				if (Musicobj.getPlaying()) {
					StopMusicBTN.setIcon(resources.getScaledImage(22, 2, 2));
				}
				else {
					StopMusicBTN.setIcon(resources.getScaledImage(23, 2, 2));
				}
			}
		});

	}

}
