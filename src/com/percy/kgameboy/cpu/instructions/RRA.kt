package com.percy.kgameboy.cpu.instructions

import com.percy.kgameboy.cpu.Flags
import com.percy.kgameboy.common.Register8
import com.percy.kgameboy.utils.isSet
import com.percy.kgameboy.utils.setBit
import com.percy.kgameboy.cpu.shiftRight

class RRA(private val a: Register8, private val flags: Flags,
          override val opCode: UByte) : Instruction {
    override val name = "RRA"
    override val description = "Rotates bits in ${a.name} to the right, through the carry flag."
    override val length = 1
    private val cycles = 4

    override fun run(operand: UByteArray) : Int {
        val bit0 = isSet(a.getUnsigned(), 0)
        shiftRight(a)
        a.set(setBit(a.getUnsigned(), 7, flags.isCarrySet()))
        flags.setZero(false)
        flags.setNegative(false)
        flags.setHalfCarry(false)
        flags.setCarry(bit0)
        return cycles
    }
}