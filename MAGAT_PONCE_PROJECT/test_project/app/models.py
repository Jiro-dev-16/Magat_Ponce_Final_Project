from django.db import models

class tableSample(models.Model):
    # recordNo is automatically created as an AutoField primary key by default
    # You generally don't need to explicitly define 'auto_created=True' for primary keys.
    recordNo = models.BigAutoField(primary_key=True) 
    name = models.CharField(max_length=200)
    # IntegerField does not take a 'max_length' argument.
    # The max value is implicitly limited by the integer type's capacity.
    age = models.IntegerField() 

    class Meta:
        db_table = "table_sample" # Good practice to explicitly name your table

    def __str__(self):
        return f"{self.recordNo} - {self.name} - {self.age}"