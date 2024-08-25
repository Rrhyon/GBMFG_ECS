package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LocationManager {

    private JFrame locFrame;
    private JTable locationTable;
    private LocationService locationService; // Assume this is initialized

    public LocationManager() {
        locationService = new LocationService(); // Or use dependency injection
    }

    public void showLocationManagerDialog(JFrame locFrame) {
        JDialog dialog = new JDialog(locFrame, "Manage Locations", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(500, 300);

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add Location");
        JButton btnEdit = new JButton("Edit Location");
        JButton btnDelete = new JButton("Delete Location");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Table for displaying locations
        locationTable = new JTable();
        refreshLocationTable();
        dialog.add(new JScrollPane(locationTable), BorderLayout.CENTER);

        // Action listeners for buttons
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLocationDialog("Add", null);
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = locationTable.getSelectedRow();
                if (selectedRow != -1) {
                    int locationId = (int) locationTable.getValueAt(selectedRow, 0);
                    Location location = locationService.getLocationById(locationId);
                    showLocationDialog("Edit", location);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please select a location to edit.");
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = locationTable.getSelectedRow();
                if (selectedRow != -1) {
                    int locationId = (int) locationTable.getValueAt(selectedRow, 0);
                    int confirmation = JOptionPane.showConfirmDialog(dialog,
                            "Are you sure you want to delete this location?",
                            "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                    if (confirmation == JOptionPane.YES_OPTION) {
                        locationService.removeLocation(locationId);
                        refreshLocationTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please select a location to delete.");
                }
            }
        });

        dialog.setVisible(true);
    }

    private void refreshLocationTable() {
        List<Location> locations = locationService.getAllLocations();
        String[] columnNames = {"Location ID", "Name", "Description"};
        Object[][] data = new Object[locations.size()][3];

        for (int i = 0; i < locations.size(); i++) {
            Location loc = locations.get(i);
            data[i][0] = loc.getLocationId();
            data[i][1] = loc.getName();
            data[i][2] = loc.getDescription();
        }

        locationTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private void showLocationDialog(String action, Location location) {
        JDialog locationDialog = new JDialog(locFrame, action + " Location", true);
        locationDialog.setLayout(new GridLayout(0, 2, 5, 5));
        locationDialog.setSize(400, 250);

        JTextField locIdField = new JTextField(location != null ? String.valueOf(location.getLocationId()) : "");
        JTextField locNameField = new JTextField(location != null ? location.getName() : "");
        JTextField locDescField = new JTextField(location != null ? location.getDescription() : "");

        if (action.equals("Edit")) {
            locIdField.setEditable(false);  // Prevent ID from being edited during update
        }

        locationDialog.add(new JLabel("Location ID:"));
        locationDialog.add(locIdField);
        locationDialog.add(new JLabel("Location Name:"));
        locationDialog.add(locNameField);
        locationDialog.add(new JLabel("Location Description:"));
        locationDialog.add(locDescField);

        JButton btnSave = new JButton(action);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (locNameField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(locationDialog, "Location name cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (locDescField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(locationDialog, "Location description cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int locationId = Integer.parseInt(locIdField.getText());

                    // Check if the location ID already exists
                    if (action.equals("Add") && locationService.getLocationById(locationId) != null) {
                        JOptionPane.showMessageDialog(locationDialog, "Location ID already exists. Please choose a different ID.", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (action.equals("Add")) {
                        Location newLocation = new Location(locationId, locNameField.getText(), locDescField.getText());
                        locationService.saveLocation(newLocation);
                    } else if (action.equals("Edit") && location != null) {
                        location.setName(locNameField.getText());
                        location.setDescription(locDescField.getText());
                        locationService.saveLocationUpdates(location);
                    }

                    locationDialog.dispose();
                    refreshLocationTable();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(locationDialog, "Please enter a valid number for Location ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        locationDialog.add(new JLabel()); // Spacer
        locationDialog.add(btnSave);
        locationDialog.setVisible(true);
    }
}
