package com.arnabkundu.employeedemo.ui.employee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.arnabkundu.employeedemo.data.db.entities.Employee
import com.arnabkundu.employeedemo.databinding.ItemEmployeeBinding
import com.arnabkundu.employeedemo.util.ListListener

class AdapterEmployee(private val listener: ListListener) :
    RecyclerView.Adapter<AdapterEmployee.HolderList>() {

    private var oldList = emptyList<Employee>()

    class HolderList(val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderList {
        val binding =
            ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderList(binding)
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    override fun onBindViewHolder(holder: HolderList, position: Int) {
        val item: Employee = oldList[position]
        with(holder) {
            item.let {
                binding.name.text = item.employee_name
                binding.number.text = item.employee_number
                binding.salary.text = item.employee_salary

                binding.deleteBtn.setOnClickListener {
                    listener.onDelete(item)
                }

                binding.editBtn.setOnClickListener {
                    listener.onEdit(item)
                }

            }
        }
    }

    fun setData(newList: List<Employee>) {
        val diffUtil = BeneficiaryDiffUtil(oldList, newList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldList = newList
        diffResults.dispatchUpdatesTo(this)
    }

    fun getData(): List<Employee> {
        return oldList
    }

    class BeneficiaryDiffUtil(
        private val oldList: List<Employee>,
        private val newList: List<Employee>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].uid == newList[newItemPosition].uid
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }
}