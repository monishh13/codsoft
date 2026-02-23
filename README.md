This selective notarization enables scalability at an enterprise level while maintaining the immutability of significant fraud events.

**Backend Architecture (Spring Boot-Based)**

**Core API:** Spring Boot

**REST Controllers for profile registration and monitoring**

**Service layer for risk assessment and blockchain triggers**

**Integration with Ethereum using web3j**

**Transaction hash retrieval for audit linking**

**Blockchain Interaction:**

**Ethereum (Ganache for demo / Sepolia Testnet optional)**

**Smart contracts deployed using Hardhat**

**Spring Boot calls contract methods using web3j**

**Risk Engine:**

**Python Flask microservice**

**Deterministic rule engine for demo stability**

**Optional pre-trained Scikit-learn Random Forest model for anomaly detection**

The Spring Boot backend maintains real-time risk scores. It triggers the smart contract only when risk thresholds are breached, then retrieves the transaction hash, and finally updates the investigator dashboard.

3. **Investigator Dashboard**
A sophisticated dark-mode React dashboard designed specifically for investigation:
**Real-time risk score display**
**Green/Yellow/Red status indicators**
**Activity timeline graph**
**Live blockchain transaction hash link**
**Evidence export package (off-chain metadata + immutable Tx hash)**
The dashboard UI focuses on clarity, timeliness, and auditability.
4. **3-Minute Live Demo Flow**

**Baseline (Normal):** Register a normal user. Dashboard displays Risk: 10 (Green). No blockchain write happens – proving gas optimization.

Next: "Advanced (Anomaly)"
