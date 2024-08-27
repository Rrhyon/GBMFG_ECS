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

public class CategoryManager {

    private JFrame catFrame;
    private JTable categoryTable;
    private CategoryService categoryService; // Assume this is initialized

    public CategoryManager() {
        categoryService = new CategoryService(); // Or use dependency injection
    }

    public void showCategoryManagerDialog(JFrame catFrame) {
        JDialog dialog = new JDialog(catFrame, "Manage Categories", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 300);

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add Category");
        JButton btnEdit = new JButton("Edit Category");
        JButton btnDelete = new JButton("Delete Category");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Table for displaying categories
        categoryTable = new JTable();
        refreshCategoryTable();
        dialog.add(new JScrollPane(categoryTable), BorderLayout.CENTER);

        // Action listeners for buttons
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showCategoryDialog("Add", null);
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = categoryTable.getSelectedRow();
                if (selectedRow != -1) {
                    int categoryId = (int) categoryTable.getValueAt(selectedRow, 0);
                    Category category = categoryService.getCategoryById(categoryId);
                    showCategoryDialog("Edit", category);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please select a category to edit.");
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = categoryTable.getSelectedRow();
                if (selectedRow != -1) {
                    int categoryId = (int) categoryTable.getValueAt(selectedRow, 0);
                    int confirmation = JOptionPane.showConfirmDialog(dialog,
                            "Are you sure you want to delete this category?",
                            "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                    if (confirmation == JOptionPane.YES_OPTION) {
                        categoryService.removeCategory(categoryId);
                        refreshCategoryTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please select a category to delete.");
                }
            }
        });

        dialog.setVisible(true);
    }

    private void refreshCategoryTable() {
        List<Category> categories = categoryService.getAllCategories();
        String[] columnNames = {"Category ID", "Name", "Description"};
        Object[][] data = new Object[categories.size()][3];

        for (int i = 0; i < categories.size(); i++) {
            Category cat = categories.get(i);
            data[i][0] = cat.getCategoryId();
            data[i][1] = cat.getName();
            data[i][2] = cat.getDescription();
        }

        categoryTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private void showCategoryDialog(String action, Category category) {
        JDialog categoryDialog = new JDialog(catFrame, action + " Category", true);
        categoryDialog.setLayout(new GridLayout(0, 2, 5, 5));
        categoryDialog.setSize(400, 250);

        JTextField catIdField = new JTextField(category != null ? String.valueOf(category.getCategoryId()) : "");
        JTextField catNameField = new JTextField(category != null ? category.getName() : "");
        JTextField catDescField = new JTextField(category != null ? category.getDescription() : "");

        if (action.equals("Edit")) {
            catIdField.setEditable(false);  // Prevent ID from being edited during update
        }

        categoryDialog.add(new JLabel("Category ID:"));
        categoryDialog.add(catIdField);
        categoryDialog.add(new JLabel("Category Name:"));
        categoryDialog.add(catNameField);
        categoryDialog.add(new JLabel("Category Description:"));
        categoryDialog.add(catDescField);

        JButton btnSave = new JButton(action);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (catNameField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(categoryDialog, "Category name cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (catDescField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(categoryDialog, "Category description cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int categoryId = Integer.parseInt(catIdField.getText());

                    // Check if the category ID already exists
                    if (action.equals("Add") && categoryService.getCategoryById(categoryId) != null) {
                        JOptionPane.showMessageDialog(categoryDialog, "Category ID already exists. Please choose a different ID.", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (action.equals("Add")) {
                        Category newCategory = new Category(categoryId, catNameField.getText(), catDescField.getText());
                        categoryService.saveCategory(newCategory);
                    } else if (action.equals("Edit") && category != null) {
                        category.setName(catNameField.getText());
                        category.setDescription(catDescField.getText());
                        categoryService.saveCategoryUpdates(category);
                    }

                    categoryDialog.dispose();
                    refreshCategoryTable();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(categoryDialog, "Please enter a valid number for Category ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        categoryDialog.add(new JLabel()); // Spacer
        categoryDialog.add(btnSave);
        categoryDialog.setVisible(true);
    }
}
