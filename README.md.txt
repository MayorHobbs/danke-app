# Danke! 🌟

**Daily wisdom and actionable micro-tasks for conscious living**

A mobile widget app inspired by Dan Koe's philosophy of focus, purpose, and intentional self-improvement.

---

## 🎯 What is Danke?

Danke! delivers one philosophical quote and one actionable micro-task to your phone's home screen every day. It's designed to help you:

- Build better habits through daily micro-commitments
- Develop consciousness across 4 domains: Mind, Health, Wealth, Relationships
- Stay focused on what matters most

**Not generic motivation. Deep, actionable philosophy.**

---

## 📱 Features

### MVP (In Development)
- [x] iOS home screen widget (Small 2x2)
- [x] Daily quote rotation (7 days → expanding to 364)
- [x] Actionable micro-tasks (5-30 min timeboxed)
- [x] Minimal companion app (Today, Browse, Settings tabs)
- [ ] Android widget (Glance-based)
- [ ] Lock screen widget support (iOS 16+, Android 14+)

### Roadmap (V2+)
- [ ] 52 weeks of curated content (364 days)
- [ ] Weekly synthesis view
- [ ] Domain preference selection
- [ ] Evening reflection journal
- [ ] Apple Watch complication
- [ ] Auto-updates from Dan Koe's latest posts

---

## 🏗️ Architecture

### Tech Stack

**iOS:**
- Swift 5.9+
- WidgetKit (home/lock screen widgets)
- SwiftUI (app UI)
- App Groups (widget ↔ app data sharing)

**Android:**
- Kotlin 1.9+
- Jetpack Glance (modern widget framework)
- Jetpack Compose (app UI)
- SharedPreferences (data storage)

**Content:**
- JSON-based content system (bundled with app)
- Future: GitHub-hosted for remote updates

### Repository Structure

```
danke-app/
├── ios/                    # iOS Xcode project
│   ├── Danke/             # Main app
│   ├── DankeWidget/       # Widget extension
│   └── Shared/            # Shared models & services
├── android/               # Android Studio project
│   ├── app/
│   └── widget/
├── content/               # Content database
│   ├── content.json       # Quote/action pairs
│   └── schema.json        # Data structure docs
├── docs/                  # Planning documents
│   ├── prd.md
│   ├── architecture.md
│   ├── design-system.md
│   └── implementation-plan.md
├── CHANGELOG.md
├── FEATURES.md
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

**iOS Development:**
- macOS with Xcode 15+
- Apple Developer account ($99/year for App Store, free for TestFlight)
- iOS device or simulator (iOS 16+)

**Android Development:**
- Android Studio Hedgehog+
- Android device or emulator (API 31+)

**Vibecoding Tools:**
```bash
# Install Vibeship Spawner Skills (desktop)
npx github:vibeforge1111/vibeship-spawner-skills install --mcp

# Verify installation
npx vibeship-spawner-skills list
```

### Quick Start (iOS)

```bash
# Clone repository
git clone https://github.com/MayorHobbs/danke-app.git
cd danke-app

# Open iOS project
open ios/Danke.xcodeproj

# Build and run
# Xcode → Select "Danke" scheme → Run (⌘R)
```

### Quick Start (Android)

```bash
# Open Android project in Android Studio
# File → Open → select danke-app/android/

# Sync Gradle
# Build → Make Project

# Run on emulator/device
```

---

## 📖 Development Workflow

### Vertical Slice Methodology

We build **complete features** incrementally, not layers:

**Phase 1:** Hardcoded widget (works immediately)  
**Phase 2:** Dynamic content loading (still works)  
**Phase 3:** Full app (still works)  
**Phase 4:** Android port (both platforms work)

**Never commit broken code.** Every commit should be runnable.

### Commit Conventions

```bash
# Feature additions
git commit -m "feat(ios): add Today tab with completion tracking"

# Bug fixes
git commit -m "fix(android): resolve widget refresh timing"

# Content updates
git commit -m "content: add Week 2 quotes (Days 8-14)"

# Documentation
git commit -m "docs: update architecture with Glance implementation"

# Security fixes
git commit -m "security: resolve exposed API key vulnerability"
```

**Commit frequency:** Every 15-30 minutes of working code

### Using Specialized Agents

```bash
# Spawn iOS widget specialist
"Spawn mobile/ios-widgets specialist. Build TimelineProvider with midnight refresh."

# Spawn content specialist
"Spawn content/copywriting specialist. Generate 7 Dan Koe-inspired quote/action pairs."

# Spawn security auditor
"Spawn security/mobile-security specialist. Audit this code for vulnerabilities."
```

---

## 🎨 Design System

### Color Palette (Dark Mode Primary)

```
Backgrounds:
- Primary:   #0A0A0A (near-black)
- Secondary: #1A1A1A (elevated surfaces)
- Tertiary:  #2A2A2A (cards, widgets)

Text:
- Primary:   #F5F5F5 (off-white)
- Secondary: #A0A0A0 (muted gray)
- Accent:    #E8DCC4 (warm cream for quotes)

Domains:
- Consciousness: #8B7FB8 (purple)
- Health:        #7FA66A (sage green)
- Wealth:        #C9A961 (muted gold)
- Relationships: #B87F8F (rose)
```

### Typography

- **Quotes:** Georgia (serif), 15pt, philosophical weight
- **Actions:** SF Pro / Roboto (sans), 13pt, functional clarity
- **Metadata:** 11pt, muted gray

**Principle:** Clarity over decoration. Every pixel serves a purpose.

---

## 📊 Content Schema

```json
{
  "day": 1,
  "quote": "15-50 word philosophical insight",
  "quoteSource": "Article title or concept name",
  "action": "Specific, timeboxed micro-task",
  "actionDuration": "5-30 min",
  "domain": "consciousness | health | wealth | relationships",
  "difficulty": "beginner | intermediate | advanced"
}
```

**Content Sources:**
- Dan Koe's Substack (letters.thedankoe.com)
- Analyzed and synthesized by AI
- Human-reviewed for quality and coherence

---

## 🔒 Privacy & Security

**Privacy-First Principles:**
- ✅ No user accounts (local-first)
- ✅ No tracking or analytics
- ✅ No data collection
- ✅ All reflections stored locally (device-only)

**Security Practices:**
- Pre-launch Vibeship Scanner audit
- No hardcoded secrets
- Proper widget sandbox isolation
- Regular dependency updates

---

## 📦 Distribution

### iOS
- **TestFlight:** Beta testing (launching Week 4)
- **App Store:** Public release (launching Week 6)

### Android
- **Google Play Open Testing:** Beta (launching Week 4)
- **Google Play Store:** Public release (launching Week 6)

**Pricing:** 100% free (no ads, no in-app purchases in MVP)

---

## 🤝 Contributing

This is currently a solo project, but feedback is welcome!

**Ways to help:**
- Beta test via TestFlight / Play Store Open Testing
- Suggest quote/action pairs (via Issues)
- Report bugs or UX issues
- Share with Dan Koe's community

---

## 📄 Legal

**Disclaimer:** Danke! is an **unofficial app** inspired by Dan Koe's publicly available writings. This project is not affiliated with, endorsed by, or connected to Dan Koe.

All content is original synthesis based on publicly available philosophy. No direct copying of copyrighted material.

**License:** MIT (see LICENSE file)

---

## 🙏 Credits

**Inspiration:** Dan Koe (letters.thedankoe.com)  
**Vibecoding Methodology:** @meta_alchemist  
**Built by:** @MayorHobbs

---

## 📞 Contact

- **GitHub Issues:** Bug reports and feature requests
- **Twitter/X:** @MayorHobbs (coming soon)
- **Email:** [your-email] (add if you want)

---

**Current Status:** 🏗️ In active development (Phase 1)  
**Next Milestone:** iOS MVP widget with 7-day content rotation  
**Target Launch:** 6-8 weeks from January 18, 2026

---

**Repository:** github.com/MayorHobbs/danke-app