namespace CryptoTools
{
    partial class Form1
    {
        /// <summary>
        /// Требуется переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Обязательный метод для поддержки конструктора - не изменяйте
        /// содержимое данного метода при помощи редактора кода.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.textBoxPSign = new System.Windows.Forms.TextBox();
            this.buttonDecryptSign = new System.Windows.Forms.Button();
            this.buttonEncryptSign = new System.Windows.Forms.Button();
            this.label9 = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.textBoxQSign = new System.Windows.Forms.TextBox();
            this.textBoxSourceSign = new System.Windows.Forms.TextBox();
            this.groupBox3.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.label3);
            this.groupBox3.Controls.Add(this.label4);
            this.groupBox3.Controls.Add(this.textBoxPSign);
            this.groupBox3.Controls.Add(this.buttonDecryptSign);
            this.groupBox3.Controls.Add(this.buttonEncryptSign);
            this.groupBox3.Controls.Add(this.label9);
            this.groupBox3.Controls.Add(this.label10);
            this.groupBox3.Controls.Add(this.textBoxQSign);
            this.groupBox3.Controls.Add(this.textBoxSourceSign);
            this.groupBox3.Location = new System.Drawing.Point(12, 12);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(277, 142);
            this.groupBox3.TabIndex = 10;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "RSA Signature";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(6, 75);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(78, 13);
            this.label3.TabIndex = 8;
            this.label3.Text = "Encryption Key";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(220, 56);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(13, 13);
            this.label4.TabIndex = 9;
            this.label4.Text = "p";
            // 
            // textBoxPSign
            // 
            this.textBoxPSign.Location = new System.Drawing.Point(183, 72);
            this.textBoxPSign.Name = "textBoxPSign";
            this.textBoxPSign.Size = new System.Drawing.Size(81, 20);
            this.textBoxPSign.TabIndex = 8;
            // 
            // buttonDecryptSign
            // 
            this.buttonDecryptSign.Location = new System.Drawing.Point(181, 98);
            this.buttonDecryptSign.Name = "buttonDecryptSign";
            this.buttonDecryptSign.Size = new System.Drawing.Size(81, 36);
            this.buttonDecryptSign.TabIndex = 1;
            this.buttonDecryptSign.Text = "Check";
            this.buttonDecryptSign.UseVisualStyleBackColor = true;
            this.buttonDecryptSign.Click += new System.EventHandler(this.buttonDecryptSign_Click);
            // 
            // buttonEncryptSign
            // 
            this.buttonEncryptSign.Location = new System.Drawing.Point(90, 98);
            this.buttonEncryptSign.Name = "buttonEncryptSign";
            this.buttonEncryptSign.Size = new System.Drawing.Size(81, 36);
            this.buttonEncryptSign.TabIndex = 0;
            this.buttonEncryptSign.Text = "Sign";
            this.buttonEncryptSign.UseVisualStyleBackColor = true;
            this.buttonEncryptSign.Click += new System.EventHandler(this.buttonEncryptSign_Click);
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(123, 56);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(13, 13);
            this.label9.TabIndex = 6;
            this.label9.Text = "q";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(6, 36);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(28, 13);
            this.label10.TabIndex = 5;
            this.label10.Text = "Text";
            // 
            // textBoxQSign
            // 
            this.textBoxQSign.Location = new System.Drawing.Point(92, 72);
            this.textBoxQSign.Name = "textBoxQSign";
            this.textBoxQSign.Size = new System.Drawing.Size(81, 20);
            this.textBoxQSign.TabIndex = 3;
            // 
            // textBoxSourceSign
            // 
            this.textBoxSourceSign.Location = new System.Drawing.Point(90, 33);
            this.textBoxSourceSign.Name = "textBoxSourceSign";
            this.textBoxSourceSign.Size = new System.Drawing.Size(174, 20);
            this.textBoxSourceSign.TabIndex = 2;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(300, 160);
            this.Controls.Add(this.groupBox3);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Form1";
            this.Text = "CryptoTools";
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox textBoxPSign;
        private System.Windows.Forms.Button buttonDecryptSign;
        private System.Windows.Forms.Button buttonEncryptSign;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.TextBox textBoxQSign;
        private System.Windows.Forms.TextBox textBoxSourceSign;
    }
}

